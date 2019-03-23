package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteOrder implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("entity", EntityType.ORDER);
            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);
            if (request.getParameter("orderId") != null) {

                Integer id = Integer.valueOf(request.getParameter("orderId"));
                Order order = orderService.getById(id);
                orderService.delete(order);
                return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST,request);
            }
            else {
                request.setAttribute("error", "order.error.delete_order");
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST, request);
            }


        } catch (ServiceException e) {
            request.setAttribute("error","order.error.delete_order");
            return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_ORDER_LIST,request);


        }
    }
}
