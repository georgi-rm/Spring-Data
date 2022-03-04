import entities.Bike;
import entities.Car;
import entities.Plane;
import entities.Truck;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_vehicle");
        EntityManager entityManager = factory.createEntityManager();

        Bike bike = new Bike("Bike", "BMX", BigDecimal.valueOf(246.34), "None");
        Car car = new Car("Peugeot", "307", BigDecimal.valueOf(9345.34), "Diesel", 7);
        Plane plane = new Plane("AirBus", "A320", BigDecimal.valueOf(24000123.45), "Kerosene", 320);
        Truck truck = new Truck("Volvo", "FH16", BigDecimal.valueOf(324567.89), "Diesel", 312987.67);

        entityManager.getTransaction().begin();

        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(plane);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
