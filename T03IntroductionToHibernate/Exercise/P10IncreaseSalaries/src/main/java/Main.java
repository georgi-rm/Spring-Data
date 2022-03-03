import entities.Employee;
import entities.Project;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager.createQuery("SELECT e FROM Employee AS e" +
                        " WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            employee.setSalary(employee.getSalary().multiply(new BigDecimal("1.12")));
        }

        entityManager.flush();

        entityManager.getTransaction().commit();

        for (Employee employee : employees) {
            System.out.printf("%s %s ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

        entityManager.close();
        factory.close();
    }
}
