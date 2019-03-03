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


public class CommandUpdateOrder implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);


            Order order = new Order();
            order.setIdUser(Integer.valueOf(request.getParameter("idUser")));
            order.setIdPointHireBicycle(Integer.valueOf(request.getParameter("idPointHIreBicycle")));
            order.setRentalTime(Integer.valueOf(request.getParameter("rentalTime")));
            order.setTimeOrder(request.getParameter("timeOrder"));
            orderService.add(order);

            orderService.update(order);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command="+CommandType.UPDATE_ORDER, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't update order.", e);
        }
    }
}
