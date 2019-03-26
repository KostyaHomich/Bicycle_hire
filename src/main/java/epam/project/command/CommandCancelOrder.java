package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandCancelOrder implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandCancelOrder.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);

            if (request.getParameter("orderId") != null) {

                int order_id = Integer.valueOf(request.getParameter("orderId"));
                orderService.cancelOrder(order_id);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST, request);

            } else {
                request.setAttribute("error", "order.error.cancel_order");
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST, request);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to cancel order.", e);
            request.setAttribute("error", "order.error.cancel_order");
            throw new CommandException("Failed to cancel order.");
        }
    }
}
