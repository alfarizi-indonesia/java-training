package org.gradle.fins.model;

import javax.persistence.*;

@Entity
@Table(name = "TCB_FINS_CAS_DNR")
public class VoucherModel {

    @Id
    private String CRV_NUMBER;
    private String CURRENCY_FROM;
    private String CURRENCY_TO;
    private String AMOUNT;
    private String BANK_OUTGOING;
    private String BENEFICIARY_ACCOUNT;
    private String BENEFICIARY_NAME;

    public String getCRVNumber() {
        return this.CRV_NUMBER;
    }
    public String getCurrencyFrom() {
        return this.CURRENCY_FROM;
    }
    public String getCurrencyTo() {
        return this.CURRENCY_TO;
    }
    public String getAmount() {
        return this.AMOUNT;
    }
    public String getBankOutgoing() {
        return this.BANK_OUTGOING;
    }
    public String getBeneficiaryAccount() {
        return this.BENEFICIARY_ACCOUNT;
    }
    public String getBeneficiaryName() {
        return this.BENEFICIARY_NAME;
    }

    public void setCRVNumber(String crvNumber) {
        this.CRV_NUMBER = crvNumber;
    }
    public void setCurrencyFrom(String currencyFrom) {
        this.CURRENCY_FROM = currencyFrom;
    }
    public void setCurrencyTo(String currencyTo) {
        this.CURRENCY_TO = currencyTo;
    }
    public void setAmount(String amount) {
        this.AMOUNT = amount;
    }
    public void setBankOutgoing(String bankOutgoing) {
        this.BANK_OUTGOING = bankOutgoing;
    }
    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.BENEFICIARY_ACCOUNT = beneficiaryAccount;
    }
    public void setBeneficiaryName(String beneficiaryName) {
        this.BENEFICIARY_NAME = beneficiaryName;
    }
}