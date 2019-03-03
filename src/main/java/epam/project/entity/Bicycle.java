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

    public Bicycle(){
        this.status="available";
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
