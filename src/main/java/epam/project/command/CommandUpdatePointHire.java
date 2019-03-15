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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CommandUpdatePointHire implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            PointHireValidator pointHireValidator = (PointHireValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.POINT_HIRE);

            PointHireBuilder pointHireBuilder = new PointHireBuilder();

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = pointHireValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                PointHire pointHire = pointHireBuilder.build(parameters);
                pointHireService.update(pointHire);

                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST, request);

            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_DETAILS, request);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Error: failed to update point hire");
            return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_DETAILS, request);
        }


    }
}
