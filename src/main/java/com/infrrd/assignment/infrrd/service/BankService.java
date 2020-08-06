package com.infrrd.assignment.infrrd.service;
import com.infrrd.assignment.infrrd.bean.BankAccount;

public interface BankService {

    public BankAccount depositMoney(BankAccount b);

    public BankAccount withdrawMoney(BankAccount b);

    public BankAccount checkBalance(BankAccount b);

}
