import entities.*;
import enumarations.BillingDetailType;
import enumarations.CardType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu_bills_payment_system");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        User userOne = new User("Ivan", "Ivanov", "ivan@abv.bg", "myPassword");
        CreditCard creditCardOne = new CreditCard(21423432L, userOne, CardType.GOLD, 1, 2022);
        CreditCard creditCardTwo = new CreditCard(439348848L, userOne, CardType.PREMIUM, 5, 2025);
        BankAccount bankAccountOne = new BankAccount(333334444L, userOne, "CCB", "12CACB4342");
        User userTwo = new User("Dimitar", "Dimitrov", "dimitar@abv.bg", "dimitarPassword");
        BankAccount bankAccountTwo = new BankAccount(35453955434L, userTwo, "UBB", "934KK934");

        userOne.addBillingDetail(creditCardOne);
        userOne.addBillingDetail(creditCardTwo);
        userOne.addBillingDetail(bankAccountOne);

        entityManager.persist(userOne);
        entityManager.persist(creditCardOne);
        entityManager.persist(creditCardTwo);
        entityManager.persist(bankAccountOne);

        userTwo.addBillingDetail(bankAccountTwo);
        entityManager.persist(userTwo);
        entityManager.persist(bankAccountTwo);

        entityManager.getTransaction().commit();

        entityManager.close();
        factory.close();
    }
}
