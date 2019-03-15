package epam.project.builder;

import epam.project.entity.Bicycle;

import java.math.BigDecimal;
import java.util.Map;

public class BicycleBuilder implements Builder<Bicycle> {
    private static final String ID = "bicycleId";
    private static final String DAILY_RENTAL_PRICE = "daily_rental_price";
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String DESCRIPTION = "description";
    private static final String POINT_HIRE_ID = "pointHireId";

    @Override
    public Bicycle build(Map<String, String> params) {
        Bicycle bicycle = new Bicycle();
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case DAILY_RENTAL_PRICE:
                    bicycle.setDaily_rental_price(BigDecimal.valueOf(Double.valueOf(value)));
                    break;
                case NAME:
                    bicycle.setName(value);
                    break;
                case DESCRIPTION:
                    bicycle.setDescription(value);
                    break;
                case STATUS:
                    bicycle.setStatus(value);
                    break;
                case ID:
                    bicycle.setId(Integer.valueOf(value));
                case POINT_HIRE_ID:
                    bicycle.setPoint_hire_id(Integer.valueOf(value));
                default:
                    break;
            }

        }

        return bicycle;
    }

}

