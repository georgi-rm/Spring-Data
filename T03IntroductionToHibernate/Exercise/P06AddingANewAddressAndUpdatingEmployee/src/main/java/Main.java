import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        entityManager.persist(newAddress);

        int entriesUpdated = entityManager
                .createQuery("UPDATE Employee e" +
                        " SET e.address = :address" +
                        " WHERE e.lastName = :lastName")
                .setParameter("address", newAddress)
                .setParameter("lastName", lastName)
                .executeUpdate();

        entityManager.getTransaction().commit();

        if (entriesUpdated > 0) {
            System.out.printf("Address is changed for %d people%n", entriesUpdated);
        } else {
            System.out.printf("There is no people with last name %s%n", lastName);
        }

        entityManager.close();
        factory.close();
    }
}
