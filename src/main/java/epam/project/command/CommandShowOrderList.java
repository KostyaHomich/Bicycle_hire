package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Order;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.OrderService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowOrderList implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandShowOrderList.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) {

        try {
            request.setAttribute("viewName", "order_list");

            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);

            List<Order> orderList;

            String amountOrders = request.getParameter("amountOrders");
            request.setAttribute("amountOrders", 5);

            if (amountOrders != null && !amountOrders.isEmpty()) {
                int count = Integer.valueOf(amountOrders);
                orderList = orderService.getOrders(count);
                request.setAttribute("amountOrders", count);
            } else {
                orderList = orderService.getOrders(5);
            }


            request.setAttribute("orderList", orderList);
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            request.setAttribute("error", "Error: failed get all orders.");
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        }


    }
}
