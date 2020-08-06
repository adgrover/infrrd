package com.infrrd.assignment.infrrd.service;

import com.infrrd.assignment.infrrd.bean.BankAccount;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BankServiceImpl implements BankService{

    public final Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());

    public static List<BankAccount> bankAccounts =Arrays.asList(
            new BankAccount("A", 12345L, 23456l),
            new BankAccount("B", 23456L, 987676l),
            new BankAccount("C", 34567L, 78765l),
            new BankAccount("D", 45678L, 987876l)
            );


    @Override
    public synchronized BankAccount depositMoney(BankAccount b) {
        LOGGER.log(Level.INFO, "Processing Deposit Request at "+System.currentTimeMillis()+" for Bankaccount "+b.getAccountNumber());
        for(BankAccount b1: bankAccounts){
            if(b1.getAccountNumber() == b.getAccountNumber()){
                b1.setAccountBalance(b1.getAccountBalance() + b.getAccountBalance());
                return b1;
            }
        }
        return null;
    }

    @Override
    public synchronized BankAccount withdrawMoney(BankAccount b) {
        LOGGER.log(Level.INFO, "Processing Withdraw Request at "+System.currentTimeMillis()+" for Bankaccount "+b.getAccountNumber());
        for(BankAccount b1: bankAccounts){
            if(b1.getAccountNumber() == b.getAccountNumber()){

                //Check to avoid negative balance
                if(b1.getAccountBalance() < b.getAccountBalance()){
                    return null;
                }
                b1.setAccountBalance(b1.getAccountBalance() - b.getAccountBalance());
                return b1;
            }
        }
        return null;
    }

    @Override
    public BankAccount checkBalance(BankAccount b) {
        LOGGER.log(Level.INFO, "Processing Check Balance Request at "+System.currentTimeMillis()+" for Bankaccount "+b.getAccountNumber());
        for(BankAccount b1 : bankAccounts ){
            if(b1.getAccountNumber().equals(b.getAccountNumber())){
                return b1;
            }
        }
        return null;
    }
}
