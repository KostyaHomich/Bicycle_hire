package epam.project.builder;

import epam.project.entity.Bicycle;
import epam.project.entity.Order;
import epam.project.entity.PointHire;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.service.impl.PointHireService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Map;

public class OrderBuilder implements Builder<Order> {

    private static final String TIME_ORDER = "time_order";
    private static final String TIME_RENTAL = "time_rental";
    private static final String POINT_HIRE_ID = "pointHireId";
    private static final String BICYCLE_ID = "bicycleId";
    private static final String STATUS = "status";
    private static final String COST = "cost";

    private static final Logger LOGGER = LogManager.getLogger(OrderBuilder.class);

    @Override
    public Order build(Map<String, String> params) {


        Order order = new Order();
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case TIME_ORDER:
                    order.setTimeOrder(value);
                    break;
                case BICYCLE_ID:
                    Bicycle bicycle=new Bicycle();
                    bicycle.setId(Integer.valueOf(value));
                    order.setBicycle(bicycle);
                    break;
                case POINT_HIRE_ID:
                    PointHire pointHire=new PointHire();
                    pointHire.setId(Integer.valueOf(value));
                    order.setPointHire(pointHire);
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