package epam.project.builder;

import epam.project.entity.Order;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Map;

public class OrderBuilder implements Builder<Order> {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String POINT_HIRE_ID = "point_hire_id";
    private static final String TIME_ORDER = "time_order";
    private static final String TIME_RENTAL = "time_rental";
    private static final String STATUS = "status";
    private static final String COST = "cost";

    private static final Logger LOGGER = LogManager.getLogger(OrderBuilder.class);

    @Override
    public Order build(Map<String, String> params) throws ServiceException {

        Order order = new Order();
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case ID:
                    order.setId(Integer.valueOf(value));
                    break;
                case USER_ID:
                    order.setIdUser(Integer.valueOf(value));
                    break;
                case POINT_HIRE_ID:
                    order.setIdPointHireBicycle(Integer.valueOf(value));
                    break;
                case TIME_ORDER:
                    order.setTimeOrder(value);
                    break;
                case TIME_RENTAL:
                    order.setRentalTime(Integer.valueOf(value));
                    break;
                case STATUS:
                    order.setStatus(value);
                    break;
                case COST:
                    order.setCost(BigDecimal.valueOf(Double.valueOf(value)));
                    break;
                default:
                    break;
            }

        }

        return order;

    }

}