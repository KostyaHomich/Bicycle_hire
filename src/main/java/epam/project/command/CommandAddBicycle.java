package epam.project.command;

import epam.project.builder.BicycleBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
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
            BicycleValidator bicycleValidator = (BicycleValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.BICYCLE);
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);

            ValidationResult validationResult = bicycleValidator.doValidate(parameters);
            BicycleBuilder bicycleBuilder = new BicycleBuilder();
            //sweet-alert
            if (validationResult.getErrors().size() == 0) {
                Bicycle bicycle = bicycleBuilder.build(parameters);
                bicycleService.add(bicycle);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST, request);
            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_DETAILS, request);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_DETAILS, request);
        }


    }
}
