import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        System.out.println("Enter town name:");
        String townName = scanner.nextLine();

        entityManager.getTransaction().begin();

        List<Town> towns = entityManager.createQuery(
                        "SELECT t FROM Town AS t" +
                                " WHERE t.name = :townName", Town.class)
                .setParameter("townName", townName)
                .getResultList();

        int deletedAddresses = 0;
        if (towns.size() > 0) {
            List<Address> addresses = entityManager.createQuery(
                            "SELECT a FROM Address AS a" +
                                    " WHERE a.town = :town", Address.class)
                    .setParameter("town", towns.get(0))
                    .getResultList();

            for (Address address : addresses) {
                entityManager.createQuery(
                                "UPDATE Employee AS e" +
                                        " SET e.address = null" +
                                        " WHERE e.address = :address")
                        .setParameter("address", address)
                        .executeUpdate();
            }

            deletedAddresses = entityManager.createQuery(
                            "DELETE FROM Address AS a" +
                                    " WHERE a.town = :town")
                    .setParameter("town", towns.get(0))
                    .executeUpdate();

            entityManager.createQuery(
                            "DELETE FROM Town AS t" +
                                    " WHERE t.name = :townName")
                    .setParameter("townName", townName)
                    .executeUpdate();
        }

        entityManager.getTransaction().commit();

        if (deletedAddresses == 1) {
            System.out.printf("%d address in %s deleted%n", deletedAddresses, townName);
        } else {
            System.out.printf("%d addresses in %s deleted%n", deletedAddresses, townName);
        }

        entityManager.close();
        factory.close();
    }
}
