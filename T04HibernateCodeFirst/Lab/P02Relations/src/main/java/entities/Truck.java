package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "trucks")
public class Truck extends Vehicle{
    @Column(name = "load_capacity")
    private Double loadCapacity;

    @ManyToMany()
    @JoinTable(name = "drivers_trucks",
    joinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"))
    private List<Driver> driver;

    public Truck() {
    }

    public Truck(String type, String model, BigDecimal price, String fuelType, Double loadCapacity, Driver driver) {
        super(type, model, price, fuelType);
        this.loadCapacity = loadCapacity;
        this.driver = new ArrayList<>();
        this.driver.add(driver);
    }

    public Double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(Double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
