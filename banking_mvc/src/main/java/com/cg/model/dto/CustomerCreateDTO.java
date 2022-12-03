package com.cg.model.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;


public class CustomerCreateDTO implements Validator {

    private Long id;

    @NotEmpty(message = "Full Name is required")
    private String fullName;

    private String email;
    private String phone;
    private String address;

    //    @Pattern(regexp = "^\\d+$", message = "Balance is accept only number")
    private String balance;

    public CustomerCreateDTO() {
    }

    public CustomerCreateDTO(Long id, String fullName, String email, String phone, String address, String balance) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CustomerCreateDTO customerCreateDTO = (CustomerCreateDTO) target;

        String balanceStr = customerCreateDTO.getBalance();
        String email = customerCreateDTO.getEmail();

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,6}$")) {
            errors.rejectValue("email", "email.matches", "Email not valid");
            return;
        }

        if (balanceStr.length() == 0) {
            errors.rejectValue("balance", "balance.length", "Balance not empty");
        }

        if (balanceStr.length() > 11 || balanceStr.length() < 2) {
            errors.rejectValue("balance", "balance.length", "Balance size must be between 2 and 11");
        }

        if (!balanceStr.matches("^\\d+$")) {
            errors.rejectValue("balance", "balance.matches", "Balance is accept only number");
            return;
        }

        double balance = Double.parseDouble(balanceStr);

        if (balance % 10 > 0) {
            errors.rejectValue("balance", "balance.percent", "Balance accept only even number");
            return;
        }

    }
}
