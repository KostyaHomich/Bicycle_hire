package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.service.RequestParameterParser;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.builder.PointHireBuilder;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
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
            request.setAttribute("entity", EntityType.POINT_HIRE);

            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            PointHireValidator pointHireValidator = (PointHireValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.POINT_HIRE);
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            PointHireBuilder pointHireBuilder = new PointHireBuilder();

            ValidationResult validationResult = pointHireValidator.doValidate(parameters);
            ResponseContent responseContent = new ResponseContent();
            if (validationResult.getErrors().size() == 0) {
                pointHireService.update(pointHireBuilder.build(parameters));
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
            request.setAttribute("error", "Error: failed to update point hire");
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }

    }
}
