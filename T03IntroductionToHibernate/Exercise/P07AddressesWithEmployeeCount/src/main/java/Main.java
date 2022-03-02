import entities.Address;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Address> addresses = entityManager.createQuery("SELECT a FROM Address AS a" +
                        " order by a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : addresses) {
            if (address.getTown() != null) {
                Hibernate.initialize(address.getTown());
            }
            Hibernate.initialize(address.getEmployees());
        }
        entityManager.getTransaction().commit();

        for (Address address : addresses) {
            System.out.printf("%s, %s - %d employees%n", address.getText(),
                    (address.getTown() == null) ? "" : address.getTown().getName(),
                    address.getEmployees().size());
        }

        entityManager.close();
        factory.close();
    }
}
