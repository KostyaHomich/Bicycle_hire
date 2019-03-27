package epam.project.command;

import epam.project.builder.OrderBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.EntityType;
import epam.project.entity.Order;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.service.impl.OrderService;
import epam.project.service.impl.UserService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.OrderValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class CommandAddOrder implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandAddOrder.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute("entity", EntityType.ORDER);

            OrderValidator orderValidator = (OrderValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.ORDER);
            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);

            UserService userService=(UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            BicycleService bicycleService=(BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = orderValidator.doValidate(parameters);

            OrderBuilder orderBuilder = new OrderBuilder();
            if (validationResult.getErrors().size() == 0) {
                Order order = orderBuilder.build(parameters);

                User singInUser=(User)request.getSession().getAttribute("signInUser");

                User user=userService.getById(singInUser.getId());
                Bicycle bicycle=bicycleService.getById(order.getBicycle().getId());

                BigDecimal cost = new BigDecimal(order.getRentalTime() * bicycle.getDaily_rental_price().intValue());
                BigDecimal userBalance=user.getBalance();
                if(userBalance.intValue()-cost.intValue()<0) {
                    request.setAttribute("error", "user.error.not_enough_money");
                    return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_DETAILS,request);
                }
                else {
                    order.setUser(user);
                    orderService.add(order);
                    user.setBalance(new BigDecimal(userBalance.intValue()-cost.intValue()));
                    request.getSession().setAttribute("signInUser",user);
                    return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST,request);
                }


            } else {
                request.setAttribute("errorsList", validationResult);
                return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST,request);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to add order.", e);
            request.setAttribute("error", "order.error.add_order");
            throw new CommandException("Failed to add order.");
        }
    }
}
