package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.service.RequestParameterParser;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.builder.BicycleBuilder;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.BicycleValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CommandUpdateBicycle implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("entity", EntityType.BICYCLE);

            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            BicycleValidator bicycleValidator = (BicycleValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.BICYCLE);
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            BicycleBuilder bicycleBuilder = new BicycleBuilder();

            ResponseContent responseContent = new ResponseContent();
            ValidationResult validationResult = bicycleValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {
                bicycleService.update(bicycleBuilder.build(parameters));
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
            request.setAttribute("error", "Error: failed to update bicycle");
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }

    }
}
