package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity(name = "trucks")
public class Truck extends Vehicle{
    @Column(name = "load_capacity")
    Double loadCapacity;

    public Truck() {
    }

    public Truck(String type, String model, BigDecimal price, String fuelType, Double loadCapacity) {
        super(type, model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
