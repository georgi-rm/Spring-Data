import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_vehicle");
        EntityManager entityManager = factory.createEntityManager();

        Bike bike = new Bike("Bike", "BMX", BigDecimal.valueOf(246.34), "None");

        PlateNumber plateNumber = new PlateNumber("H1234BA");
        Car car = new Car("Peugeot", "307", BigDecimal.valueOf(9345.34), "Diesel", 7, plateNumber);

        Company company = new Company("Wiz-Air");
        Plane plane = new Plane("AirBus", "A320", BigDecimal.valueOf(24000123.45), "Kerosene", 320, company);

        Driver driver = new Driver("Pesho");

        Truck truck = new Truck("Volvo", "FH16", BigDecimal.valueOf(324567.89), "Diesel", 312987.67, driver);

        entityManager.getTransaction().begin();

        entityManager.persist(bike);

        entityManager.persist(plateNumber);
        entityManager.persist(car);

        entityManager.persist(company);
        entityManager.persist(plane);

        entityManager.persist(driver);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
