package entities;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "bikes")
public class Bike extends Vehicle{
    public Bike() {
    }

    public Bike(String type, String model, BigDecimal price, String fuelType) {
        super(type, model, price, fuelType);
    }


}
