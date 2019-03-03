package epam.project.entity;

import epam.project.database.dao.Identified;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Order implements Identified<Integer> {
   private int id;
   private User user;
   private PointHire pointHire;
   private Bicycle bicycle;
   private String timeOrder;
   private int rentalTime;
   private String status;
   private BigDecimal cost;
   private int idPointHireBicycle;
   private int idUser;

   public Order(){
      this.status="in working";
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
