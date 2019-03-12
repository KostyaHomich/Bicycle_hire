package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.Order;
import epam.project.service.RequestParameterParser;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.builder.OrderBuilder;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.OrderValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CommandAddOrder implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("entity", EntityType.ORDER);

            ResponseContent responseContent = new ResponseContent();

            OrderValidator orderValidator = (OrderValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.ORDER);
            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = orderValidator.doValidate(parameters);
            OrderBuilder orderBuilder = new OrderBuilder();

            if (validationResult.getErrors().size() == 0) {

                Order order = orderBuilder.build(parameters);

                orderService.add(order);
                Router router = new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD);
                responseContent.setRouter(router);
                return responseContent;

            } else {
                Router router = new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD);
                request.setAttribute("errorsList", validationResult);
                responseContent.setRouter(router);
                return responseContent;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Error: failed to add order");
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }
    }
}
