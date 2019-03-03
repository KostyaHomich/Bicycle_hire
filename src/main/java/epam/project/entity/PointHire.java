package epam.project.entity;

import epam.project.database.dao.Identified;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PointHire implements Identified<Integer> {
    private int id;
    private String location;
    private String telephone;
    private String description;

    List<Bicycle> bicycleList = new ArrayList<>();

    public PointHire(){}

    public void add(Bicycle bicycle) {
        bicycleList.add(bicycle);
    }

    public void remove(Bicycle bicycle) {
        bicycleList.remove(bicycle);
    }

    public void remove(int id) {
        bicycleList.remove(id);
    }

    public void contains(Bicycle bicycle) {
        bicycleList.contains(bicycle);
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
