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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CommandUpdateBicycle implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandUpdateBicycle.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            BicycleValidator bicycleValidator = (BicycleValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.BICYCLE);

            BicycleBuilder bicycleBuilder = new BicycleBuilder();

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = bicycleValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                Bicycle bicycle = bicycleBuilder.build(parameters);
                bicycleService.update(bicycle);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_LIST,request);

            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_DETAILS,request);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to update bicycle", e);
            request.setAttribute("error", "bicycle.error.update_bicycle");
            throw new CommandException("Failed to update bicycle");
        }

    }
}
