package epam.project.command;

import epam.project.builder.OrderBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.OrderValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class CommandUpdateOrder implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandUpdateOrder.class);
    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);
            OrderValidator orderValidator = (OrderValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.ORDER);

            OrderBuilder orderBuilder = new OrderBuilder();

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = orderValidator.doValidate(parameters);
            if (validationResult.getErrors().size() == 0) {
                Order order = orderBuilder.build(parameters);
                orderService.update(order);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST, request);

            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_DETAILS, request);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to update order", e);
            request.setAttribute("error", "order.error.update_order");
            throw new CommandException("Failed to update order");
        }

    }
}
