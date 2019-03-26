package epam.project.command;

import epam.project.builder.PointHireBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.PointHireValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class CommandAddPointHire implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandAddPointHire.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            PointHireValidator pointHireValidator = (PointHireValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.POINT_HIRE);
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);

            ValidationResult validationResult = pointHireValidator.doValidate(parameters);
            PointHireBuilder pointHireBuilder = new PointHireBuilder();
            if (validationResult.getErrors().size() == 0) {
                PointHire pointHire = pointHireBuilder.build(parameters);
                pointHireService.add(pointHire);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST, request);
            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_DETAILS, request);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to add point hire.", e);
            request.setAttribute("error", "point_hire.error.add_point_hire");
            throw new CommandException("Failed to add point hire.");
        }

    }
}
