package com.infrrd.assignment.infrrd.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankAccount {

    @JsonProperty("accountName")
    private String accountName;

    @JsonProperty("accountNumber")
    private Long accountNumber;

    @JsonProperty("accountBalance")
    private Long accountBalance;

    public BankAccount(String accountName, Long accountNumber, Long accountBalance) {
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public String getAccountName() {
        return accountName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountName='" + accountName + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
