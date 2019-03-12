package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowOrderList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        try {
            request.setAttribute("entity", EntityType.ORDER);

            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);
            List<Order> orderList = orderService.takeAll();
            request.setAttribute("orderList",orderList);
            responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        } catch (ServiceException e) {
            request.setAttribute("error", "Error: failed get all orders.");
            responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }


    }
}
