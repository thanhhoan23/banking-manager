package org.example.model.dto;

import org.example.model.Customer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

public class DepositDto implements Validator {
    private Long idDeposit;
    private String transactionAmount;
    private Customer customer;

    public DepositDto() {
    }

    public DepositDto(Long idDeposit, String transactionAmount, Customer customer) {
        this.idDeposit = idDeposit;
        this.transactionAmount = transactionAmount;
        this.customer = customer;
    }

    public Long getIdDeposit() {
        return idDeposit;
    }

    public void setIdDeposit(Long idDeposit) {
        this.idDeposit = idDeposit;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        DepositDto depositDto = (DepositDto) target;
        String transactionAmount = depositDto.getTransactionAmount();
        if (transactionAmount == null) {
            errors.rejectValue("transactionAmount", "transactionAmount", "Transaction Amount can not be null");
            return;
        } else if (transactionAmount.trim().isEmpty()){
            errors.rejectValue("transactionAmount", "transactionAmount", "Transaction amount can not empty");
        } else if(transactionAmount.length()<2 || transactionAmount.length()>12) {
            errors.rejectValue("transactionAmount.length", "transactionAmount.length","Transaction amount must be between 2 and 12");
            return;
        } else if (transactionAmount.matches("^\\d+$")){
            errors.rejectValue("transactionAmount.matches", "transactionAmount.matches", "Transaction amount only accept number");
        }
    }
}
