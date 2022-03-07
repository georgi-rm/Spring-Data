package entities;

import enumarations.BillingDetailType;
import enumarations.CardType;

import javax.persistence.*;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BillingDetail {

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(name = "expiration_month")
    private int expirationMonth;

    @Column(name = "expiration_year")
    private int expirationYear;

    public CreditCard() {
    }

    public CreditCard(Long number, User owner, CardType cardType, int expirationMonth, int expirationYear) {
        super(number, owner, BillingDetailType.CREDIT_CARD);
        this.cardType = cardType;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }
}
