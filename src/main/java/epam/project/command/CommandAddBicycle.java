package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
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

public class CommandAddBicycle implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            ResponseContent responseContent = new ResponseContent();

            BicycleValidator bicycleValidator = (BicycleValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.BICYCLE);
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = bicycleValidator.doValidate(parameters);
            BicycleBuilder bicycleBuilder = new BicycleBuilder();


            if (validationResult.getErrors().size() == 0) {

                Bicycle bicycle = bicycleBuilder.build(parameters);

                bicycleService.add(bicycle);


                Router router = new Router(PageConst.BICYCLE_LIST_PAGE_PATH, Router.Type.FORWARD);
                responseContent.setRouter(router);
                return responseContent;

            } else {
                Router router = new Router(PageConst.BICYCLE_DETAILS_PAGE_PATH, Router.Type.FORWARD);
                request.setAttribute("errorsList", validationResult);
                responseContent.setRouter(router);
                return responseContent;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.BICYCLE_DETAILS_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }


    }
}
