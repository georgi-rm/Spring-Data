import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager
                .createQuery("SELECT e FROM Employee e" +
                        " WHERE e.department.name = 'Research and Development'" +
                        " ORDER BY e.salary ASC, e.id ASC", Employee.class)
                .getResultList();

        entityManager.getTransaction().commit();

        for (Employee employee : employees) {
            System.out.printf("%s %s from %s - $%.2f%n", employee.getFirstName(),
                    employee.getLastName(), employee.getDepartment().getName(), employee.getSalary() );
        }

        entityManager.close();
        factory.close();
    }
}
