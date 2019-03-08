package epam.project.entity;

import epam.project.database.dao.Identified;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class User implements Identified<Integer> {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String status;
    private String registrationDate;
    private BigDecimal balance;
    private String email;
    private String role;


    public User() {
        this.role="user";
        this.balance=new BigDecimal(0);
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
