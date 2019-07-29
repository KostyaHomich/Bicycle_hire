package epam.project.entity;

import epam.project.database.dao.Identified;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class User implements Identified<Integer> {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String status;
    private String registrationDate;
    private String email;
    private String role;


    public User() {
        this.role = UserRole.USER.name();
        this.status="not conformed";
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
