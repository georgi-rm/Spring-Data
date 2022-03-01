import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<String> namesOfEmployees = entityManager.createQuery("SELECT e.firstName FROM Employee e" +
                        " WHERE e.salary > 50000", String.class)
                .getResultList();

        entityManager.getTransaction().commit();

        for (String name : namesOfEmployees) {
            System.out.println(name);
        }

        entityManager.close();
        factory.close();
    }
}
