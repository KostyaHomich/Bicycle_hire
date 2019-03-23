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
    private static final int DEFAULT_AMOUNT_ORDERS=5;
    private static final String AMOUNT_ORDERS="amountOrders";

    @Override
    public ResponseContent execute(HttpServletRequest request) {

        try {
            request.setAttribute("viewName", "order_list");

            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);

            List<Order> orderList;

            String amountOrders = request.getParameter(AMOUNT_ORDERS);
            request.setAttribute(AMOUNT_ORDERS, DEFAULT_AMOUNT_ORDERS);

            if (amountOrders != null && !amountOrders.isEmpty()) {
                int count = Integer.valueOf(amountOrders);
                orderList = orderService.getOrders(count);
                request.setAttribute(AMOUNT_ORDERS, count);
            } else {
                orderList = orderService.getOrders(DEFAULT_AMOUNT_ORDERS);
            }
            request.setAttribute("orderList", orderList);
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            request.setAttribute("error", "page.error.show_order_list");
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        }


    }
}
