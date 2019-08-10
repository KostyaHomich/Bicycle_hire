package epam.project.entity;

import epam.project.database.dao.Identified;
import lombok.Data;

@Data
public class Bicycle implements Identified<Integer> {

    private int id;

    private String name;
    private String link;
    private String description;
    private int pointHireId;

    public Bicycle(){
        this.pointHireId=0;
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
