package entities;

import enumarations.BillingDetailType;

import javax.persistence.*;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(nullable = false)
    protected Long number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    protected User owner;

    @Enumerated(EnumType.STRING)
    protected BillingDetailType type;

    public BillingDetail() {
    }

    public BillingDetail(Long number, User owner, BillingDetailType type) {
        this.number = number;
        this.owner = owner;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public BillingDetailType getType() {
        return type;
    }

    public void setType(BillingDetailType type) {
        this.type = type;
    }
}
