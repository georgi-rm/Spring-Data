import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Town> resultList = entityManager.createQuery("SELECT t FROM Town t", Town.class)
                .getResultList();


        for (Town town : resultList) {
            String townName = town.getName();
            if (townName.length() > 5) {
                entityManager.detach(town);
            } else {
                townName = townName.toUpperCase();
                town.setName(townName);
            }
        }

        entityManager.flush();

        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
