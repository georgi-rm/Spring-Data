import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        System.out.println("Enter start pattern for name:");
        String pattern = scanner.nextLine();

        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee AS e" +
                        " WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + "%")
                .getResultList();

        entityManager.getTransaction().commit();

        for (Employee employee : employees) {
            System.out.printf("%s %s - %s - ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary());
        }

        entityManager.close();
        factory.close();
    }
}
