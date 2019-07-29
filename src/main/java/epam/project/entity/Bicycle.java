package epam.project.entity;

import epam.project.database.dao.Identified;
import lombok.Data;

@Data
public class Bicycle implements Identified<Integer>,Comparable {

    private int id;

    private String name;
    private String link;
    private String description;
    private int point_hire_id;

    public Bicycle(){
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

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            return -1;
        }
        Bicycle bicycle = (Bicycle) o;
        if (bicycle.getDescription().equalsIgnoreCase(this.getDescription())
                && bicycle.getLink().equalsIgnoreCase(this.getLink())
                && bicycle.getName().equalsIgnoreCase(this.getName())) {
            return 1;
        }
            return -1;
    }
}
