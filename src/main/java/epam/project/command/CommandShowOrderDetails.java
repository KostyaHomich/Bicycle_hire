package epam.project.command;

import epam.project.builder.OrderBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class CommandShowOrderDetails implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandShowOrderDetails.class);
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("viewName","order_details");

            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            OrderBuilder orderBuilder = new OrderBuilder();
            int id = Integer.valueOf(request.getParameter("orderId"));

            if (id == 0) {
                Order order = orderBuilder.build(parameters);
                return setAttribute(request, order);
            } else {
                Order order = orderService.getById(id);
                return setAttribute(request, order);
            }
        } catch (ServiceException e) {
            return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, Order order) {
        request.setAttribute("order", order);

        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
    }
}
