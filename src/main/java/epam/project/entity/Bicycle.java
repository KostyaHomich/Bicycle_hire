package epam.project.entity;

import epam.project.database.dao.Identified;
import lombok.*;

import java.math.BigDecimal;

@Data
public class Bicycle implements Identified<Integer> {

    private BigDecimal daily_rental_price;
    private int id;

    private String name;

    private String status;

    private String description;
    private int point_hire_id;

    public Bicycle(){
        this.status="available";
        this.point_hire_id=0;

    }

    @Override
    public Integer getId() {
        return id;
    }


    @Override
    public void setId(int id) {
        this.id=id;
    }

}
