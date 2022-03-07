package entities;

import enumarations.BillingDetailType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount extends BillingDetail {

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "swift_code")
    private String swiftCode;

    public BankAccount() {
    }

    public BankAccount(Long number, User owner, String bankName, String swiftCode) {
        super(number, owner, BillingDetailType.BANK_ACCOUNT);
        this.bankName = bankName;
        this.swiftCode = swiftCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }
}
