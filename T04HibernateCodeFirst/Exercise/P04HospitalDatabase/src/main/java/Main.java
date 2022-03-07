import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_hospital_database");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        String input = enterCommand(scanner);

        while (!input.equals("End")) {

            switch (input) {
                case "Patient":
                    Patient patient = enterPatientData(scanner);
                    entityManager.getTransaction().begin();
                    entityManager.persist(patient);
                    entityManager.getTransaction().commit();
                    break;
                case "Visitation":
                    Visitation visitation = enterVisitationData(scanner);
                    entityManager.getTransaction().begin();
                    entityManager.persist(visitation.getComment());
                    entityManager.persist(visitation);
                    entityManager.getTransaction().commit();
                    break;
                case "Diagnose":
                    Diagnose diagnose = enterDiagnoseData(scanner);
                    entityManager.getTransaction().begin();
                    entityManager.persist(diagnose.getComment());
                    entityManager.persist(diagnose);
                    entityManager.getTransaction().commit();
                    break;
                case "Prescription":
                    Medicament medicament = enterMedicamentData(scanner);
                    entityManager.getTransaction().begin();
                    entityManager.persist(medicament);
                    entityManager.getTransaction().commit();
                    break;
                default:
                    System.out.println("Invalid command: " + input);
                    break;
            }

            input = enterCommand(scanner);
        }

        entityManager.close();
        factory.close();
    }

    private static Patient enterPatientData(Scanner scanner) {
        System.out.println("Enter first name of patient:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name of patient:");
        String lastNmae = scanner.nextLine();

        System.out.println("Enter address of patient:");
        String address = scanner.nextLine();

        System.out.println("Enter email of patient:");
        String email = scanner.nextLine();

        System.out.println("Enter date of birth of patient:");
        LocalDate dateOfBirth = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter url for picture:");
        String pictureUrl = scanner.nextLine();

        System.out.println("Is patient insured? (Yes/No)");
        String insuredInput = scanner.nextLine();
        boolean isInsured = insuredInput.equals("Yes");

        return new Patient(firstName, lastNmae, address, email, dateOfBirth, pictureUrl, isInsured);
    }

    private static Visitation enterVisitationData(Scanner scanner) {
        System.out.println("Enter date of visitation:");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter comment for visitation:");
        String commentText = scanner.nextLine();

        return new Visitation(date, new Comment(commentText));
    }

    private static Diagnose enterDiagnoseData(Scanner scanner) {
        System.out.println("Enter name of diagnose:");
        String name = scanner.nextLine();

        System.out.println("Enter comment for diagnose:");
        String commentText = scanner.nextLine();

        return new Diagnose(name, new Comment(commentText));
    }

    private static Medicament enterMedicamentData(Scanner scanner) {
        System.out.println("Enter name of medicament:");
        String name = scanner.nextLine();

        return new Medicament(name);
    }

    private static String enterCommand(Scanner scanner) {
        System.out.println("Enter command (Patient, Visitation, Diagnose, Prescription, End):");
        return scanner.nextLine();
    }
}
