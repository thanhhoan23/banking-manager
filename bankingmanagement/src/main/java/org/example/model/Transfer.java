package org.example.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfers")
public class Transfer extends ModelGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransfer;

    private Integer fees;

    @Column(name = "fees_amount", precision =12, scale = 0, nullable = false)
    private BigDecimal feesAmount;

    @Column(name = "transaction_amount", precision = 12, scale = 0, nullable = false)
    private BigDecimal transactionAmount;

    @Column(name = "transfer_amount",precision = 12, scale = 0, nullable = false)
    private BigDecimal transferAmount;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id",nullable = false)
    private Customer recipient;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "sender_id", referencedColumnName = "id", nullable = false)
    private Customer sender;

    public Transfer() {
    }

    public Transfer(Long idTransfer, Integer fees, BigDecimal feesAmount, BigDecimal transactionAmount, BigDecimal transferAmount, Customer recipient, Customer sender) {
        this.idTransfer = idTransfer;
        this.fees = fees;
        this.feesAmount = feesAmount;
        this.transactionAmount = transactionAmount;
        this.transferAmount = transferAmount;
        this.recipient = recipient;
        this.sender = sender;
    }

    public Long getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(Long idTransfer) {
        this.idTransfer = idTransfer;
    }

    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public BigDecimal getFeesAmount() {
        return feesAmount;
    }

    public void setFeesAmount(BigDecimal feesAmount) {
        this.feesAmount = feesAmount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }
}
