import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_sales_db");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Product product = new Product("Maslo", 123.0, BigDecimal.valueOf(4.50));
        Customer customer = new Customer("Ivan", "ivan@abv.bg", "54666132116516");
        StoreLocation location = new StoreLocation("London");
        Date date = new Date();
        Sale sale = new Sale(product, customer, location, date);

        Customer secondCustomer = new Customer("Pesho", "pesho@abv.bg", "11666565555566");
        Sale secondSale = new Sale(product, secondCustomer, location, date);

        Product secondProduct = new Product("Hlqb", 1.0, BigDecimal.valueOf(1.50));
        StoreLocation secondLocation = new StoreLocation("Sofia");
        Sale thirdSale = new Sale(secondProduct, secondCustomer, secondLocation, date);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(location);
        entityManager.persist(sale);
        entityManager.persist(secondProduct);
        entityManager.persist(secondCustomer);
        entityManager.persist(secondLocation);
        entityManager.persist(secondSale);
        entityManager.persist(thirdSale);

        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
