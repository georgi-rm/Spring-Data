import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_university_system");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        Teacher teacher = new Teacher("Ivan", "Ivanov", "0888888888",
                "ivan_ivanov@abv.bg", 10.0);

        Student student = new Student("Petar", "Petrov", "0878888888",
                5.5, 4);

        Course course = new Course("Spring Data", "Course at Soft Uni", LocalDateTime.now(),
                LocalDateTime.of(2022, 5,16, 1, 1),
                12, teacher);

        Course secondCourse = new Course("MySQL", "MySQL course at Soft Uni", LocalDateTime.now(),
                LocalDateTime.of(2022, 2,14, 4, 56),
                7, teacher);

        student.addCourse(course);
        student.addCourse(secondCourse);
        course.addStudents(student);

        entityManager.persist(teacher);
        entityManager.persist(student);
        entityManager.persist(course);
        entityManager.persist(secondCourse);


        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
