package epam.project.entity;

import epam.project.database.dao.Identified;

import epam.project.dto.PointHireBicycle;
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
   private PointHireBicycle pointHireBicycle;


   public Order(){
      this.status="in working";
      this.rentalTime=1;
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
