package epam.project.command;

import epam.project.builder.OrderBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.service.impl.OrderService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.OrderValidator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

import static javafx.application.Platform.exit;

public class CommandShowOrderDetailsSecondStage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("viewName", "order_details");

            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            OrderBuilder orderBuilder = new OrderBuilder();

            OrderValidator orderValidator = (OrderValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.ORDER);
            ValidationResult validationResult = orderValidator.doValidate(parameters);
            Order order = orderBuilder.build(parameters);
            if (validationResult.getErrors().size() == 0) {
                if (order.getId() == 0 ) {
                    Bicycle bicycle = bicycleService.getById(order.getBicycle().getId());
                    BigDecimal cost = new BigDecimal(order.getRentalTime() * bicycle.getDaily_rental_price().intValue());
                    order.setCost(cost);

                    return setAttribute(request, order);
                } else {
                    Order orderById = orderService.getById(order.getId());
                    return setAttribute(request, orderById);
                }
            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Failed show order details");
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ORDER_DETAILS_SECOND_PAGE_PATH);
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, Order order) {
        request.setAttribute("order", order);
        return ResponseContentBuilder.buildForwardResponseContent(PageConst.ORDER_DETAILS_SECOND_PAGE_PATH);
    }
}
