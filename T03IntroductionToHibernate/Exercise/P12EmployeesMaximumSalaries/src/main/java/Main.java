import entities.Department;
import entities.Employee;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Department> departments = entityManager.createQuery("SELECT d FROM Department AS d", Department.class)
                .getResultList();

        for (Department department : departments) {
            Hibernate.initialize(department.getEmployees());
        }

        entityManager.getTransaction().commit();

        for (Department department : departments) {
            BigDecimal maxSalary = new BigDecimal("0");

            for (Employee employee : department.getEmployees()) {
                maxSalary = maxSalary.max(employee.getSalary());
            }

            if ((maxSalary.compareTo(new BigDecimal("70000")) > 0) ||
                    (maxSalary.compareTo(new BigDecimal("30000")) < 0) ) {
                System.out.printf("%s %s%n", department.getName(), maxSalary);
            }
        }

        entityManager.close();
        factory.close();
    }
}
