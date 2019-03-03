package epam.project.controller.command;

import epam.project.controller.command.CommandType;
import epam.project.dto.ResponseContent;
import epam.project.controller.command.Command;
import epam.project.controller.command.Router;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteOrder implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);

            Integer id = Integer.valueOf(request.getParameter("id"));
            Order order = orderService.takeOrder(id);
            orderService.delete(order);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command="+CommandType.DELETE_ORDER, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't delete order.", e);
        }
    }
}
