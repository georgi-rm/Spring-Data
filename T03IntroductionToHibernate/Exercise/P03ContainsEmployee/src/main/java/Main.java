import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        System.out.println("Enter first and last name of employee:");
        String[] fullName = scanner.nextLine().split("\\s+");
        String firstName = fullName[0];
        String lastName = fullName[1];

        entityManager.getTransaction().begin();

        Long countOfEmployees = entityManager.createQuery("SELECT count(e) FROM Employee e" +
                        " WHERE e.firstName = :firstName AND e.lastName = :lastName", Long.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getSingleResult();

        entityManager.getTransaction().commit();

        if (countOfEmployees > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.close();
        factory.close();
    }
}
