package epam.project.command.bicycle;

import epam.project.builder.BicycleBuilder;
import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.CommandType;
import epam.project.command.PageConst;
import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
import epam.project.validation.ValidationResult;
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
            BicycleService bicycleService = new BicycleService();
            BicycleValidator bicycleValidator = new BicycleValidator();

            BicycleBuilder bicycleBuilder = new BicycleBuilder();

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = bicycleValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                Bicycle bicycle = bicycleBuilder.build(parameters);
                bicycleService.update(bicycle);
                return sendTolLastPage(request);

            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
            }

        } catch (ServiceException e) {
            LOGGER.error("Failed to update bicycle", e);
            request.setAttribute("error", "bicycle.error.update_bicycle");
            throw new CommandException("Failed to update bicycle");
        }

    }

    private ResponseContent sendTolLastPage(HttpServletRequest request) throws CommandException {
        String lastCommand = request.getParameter("lastCommand");
        switch (lastCommand) {
            case "SHOW_POINT_HIRE_LIST":
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST, request);
            case "SHOW_BICYCLE_LIST":
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_LIST, request);
            default:
                throw new CommandException("Last page are not present");
        }
    }
}
