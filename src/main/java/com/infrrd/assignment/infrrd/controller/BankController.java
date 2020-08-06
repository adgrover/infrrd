package com.infrrd.assignment.infrrd.controller;

import com.infrrd.assignment.infrrd.bean.BankAccount;
import com.infrrd.assignment.infrrd.constants.ApplicationConstants;
import com.infrrd.assignment.infrrd.service.BankServiceImpl;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/banking")
public class BankController {

    @Autowired
    public BankServiceImpl bankService;

    public final Logger LOGGER = Logger.getLogger(this.getClass().getSimpleName());

    Bandwidth limit = Bandwidth.classic(1, Refill.intervally(1, Duration.ofSeconds(2)));
    Bucket bucket = Bucket4j.builder()
            .addLimit(limit)
            .build();


    @PostMapping("/deposit")
    public String depositMoney(HttpServletResponse response, @RequestBody BankAccount bankAccount){
        LOGGER.log(Level.INFO, "Deposit Request recieved at "+System.currentTimeMillis()+" for Bankaccount "+bankAccount.getAccountName());


        if(bucket.tryConsume(1)) {
            BankAccount b = bankService.depositMoney(bankAccount);
            if (b == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return ApplicationConstants.TRANSACTION_FAIL;
            }
            return ApplicationConstants.DEPOSIT_SUCCESS;
        }
        response.setStatus(ApplicationConstants.STATUS_TOO_MANY_REQUESTS);
        return ApplicationConstants.TOO_MANY_REQUESTS;
    }

    @PostMapping("/withdraw")
    public String withdrawMoney(HttpServletResponse response, @RequestBody BankAccount bankAccount){
        LOGGER.log(Level.INFO, "Withdraw Request recieved at "+System.currentTimeMillis()+" for Bankaccount "+bankAccount.getAccountName());

        if(bucket.tryConsume(1)) {
            BankAccount b = bankService.withdrawMoney(bankAccount);
            if (b == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return ApplicationConstants.TRANSACTION_FAIL;
            }
            return ApplicationConstants.WITHDRAW_SUCCESS;
        }
        response.setStatus(ApplicationConstants.STATUS_TOO_MANY_REQUESTS);
        return ApplicationConstants.TOO_MANY_REQUESTS;
    }

    @PostMapping("/checkBalance")
    public String checkBalance(HttpServletResponse response, @RequestBody BankAccount bankAccount){
        LOGGER.log(Level.INFO, " Check Balance Request recieved at "+System.currentTimeMillis()+" for Bankaccount "+bankAccount.getAccountNumber());

        if(bucket.tryConsume(1)) {
            BankAccount b = bankService.checkBalance(bankAccount);
            if (b == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return ApplicationConstants.NO_ACCOUNT_FOUND;
            }
            return b.toString();
        }
        response.setStatus(ApplicationConstants.STATUS_TOO_MANY_REQUESTS);
        return ApplicationConstants.TOO_MANY_REQUESTS;
    }

}
