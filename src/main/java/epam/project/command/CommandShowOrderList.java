package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Order;
import epam.project.entity.User;
import epam.project.entity.UserRole;
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
    private static final int DEFAULT_AMOUNT_ORDERS = 5;
    private static final String AMOUNT_ORDERS = "amountOrders";

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {

        try {
            request.setAttribute("viewName", "order_list");

            OrderService orderService = (OrderService) ServiceFactory.getInstance().getService(ServiceType.ORDER);

            List<Order> orderList;
            User user = (User) request.getSession().getAttribute("signInUser");
            String amountOrders = request.getParameter(AMOUNT_ORDERS);
            request.setAttribute(AMOUNT_ORDERS, DEFAULT_AMOUNT_ORDERS);

            if (amountOrders != null && !amountOrders.isEmpty()) {
                int count = Integer.valueOf(amountOrders);

                if (user.getRole().equalsIgnoreCase(UserRole.ADMIN.name())) {
                    orderList = orderService.getOrders(count);
                }
                else {
                    orderList=orderService.takeAllOrderByUserPk(user.getId(),count);
                }
                request.setAttribute(AMOUNT_ORDERS, count);
            } else {
                if (user.getRole().equalsIgnoreCase(UserRole.ADMIN.name())) {
                    orderList = orderService.getOrders(DEFAULT_AMOUNT_ORDERS);
                }
                else {
                    orderList=orderService.takeAllOrderByUserPk(user.getId(),DEFAULT_AMOUNT_ORDERS);
                }
            }
            request.setAttribute("orderList", orderList);
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Failed to show order list", e);
            request.setAttribute("error", "page.error.show_order_list");
            throw new CommandException("Failed to show order list");

        }


    }
}
