package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;

import javax.servlet.http.HttpServletRequest;

public class CommandShowOrderDetails implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("entity", EntityType.ORDER);

            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);
            int id = Integer.valueOf(request.getParameter("orderId"));

            if (id == 0) {
                Order order = new Order();
                return setAttribute(request, order);
            } else {
                Order order = orderService.getById(id);
                return setAttribute(request, order);
            }
        } catch (ServiceException e) {
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, Order order) {
        request.setAttribute("order", order);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD));
        return responseContent;
    }
}
