package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "withdraws")
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWithDraw;
    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transactionAmount;
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    public Withdraw() {
    }

    public Withdraw(Long idWithDraw, BigDecimal transactionAmount, Customer customer) {
        this.idWithDraw = idWithDraw;
        this.transactionAmount = transactionAmount;
        this.customer = customer;
    }

    public Long getIdWithDraw() {
        return idWithDraw;
    }

    public void setIdWithDraw(Long idWithDraw) {
        this.idWithDraw = idWithDraw;
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
