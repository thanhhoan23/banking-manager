package org.example.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name= "deposits")
public class Deposit extends ModelGeneral{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeposit;
    @Column(name = "transaction_amount", precision = 12, scale = 1, nullable = false)
    private BigDecimal transactionAmount;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id",nullable = false)
    private Customer customer;
    public Deposit() {}

    public Deposit(Long idDeposit, BigDecimal transactionAmount, Customer customer) {
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

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}

