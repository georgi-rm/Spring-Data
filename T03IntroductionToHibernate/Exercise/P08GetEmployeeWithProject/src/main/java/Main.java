import entities.Employee;
import entities.Project;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        System.out.println("Enter id of employee:");
        int idOfEmployee = Integer.parseInt(scanner.nextLine());

        entityManager.getTransaction().begin();

        Employee employee = entityManager.createQuery("SELECT e FROM Employee AS e" +
                        " WHERE e.id = :employeeId", Employee.class)
                .setParameter("employeeId", idOfEmployee)
                .getSingleResult();

        Hibernate.initialize(employee.getProjects());
        entityManager.getTransaction().commit();

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

        employee.getProjects().stream()
                .map(Project::getName)
                .sorted()
                .forEach(p -> System.out.printf("\t%s%n", p));

        entityManager.close();
        factory.close();
    }
}
