package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "planes")
public class Plane extends Vehicle{
    @Column(name = "passenger_capacity")
    Integer passengerCapacity;

    public Plane() {
    }

    public Plane(String type, String model, BigDecimal price, String fuelType, Integer passengerCapacity) {
        super(type, model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}
