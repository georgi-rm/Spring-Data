import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Project> projects = entityManager.createQuery("SELECT p FROM Project AS p" +
                        " ORDER BY p.startDate DESC, p.name ASC", Project.class)
                .setMaxResults(10)
                .getResultList();

        entityManager.getTransaction().commit();

        for (Project project : projects) {
            System.out.printf("Project name: %s%n", project.getName());
            System.out.printf(" \tProject Description: %s%n", project.getDescription());

            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

            System.out.printf(" \tProject Start Date:%s%n",
                    project.getStartDate().format(customFormatter));
            System.out.printf(" \tProject End Date: %s%n",
                    (project.getEndDate() == null) ? "null" : project.getEndDate().format(customFormatter));
        }

        entityManager.close();
        factory.close();
    }
}
