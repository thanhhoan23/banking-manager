package org.example.model.dto;

import org.example.model.Customer;

import java.math.BigDecimal;

public class TransferDto {
    private Long idTransfer;
    private Integer fees;
    private BigDecimal feesAmount;
    private BigDecimal transactionAmount;
    private BigDecimal transferAmount;
    private Customer recipient;
    private Customer sender;

    public TransferDto() {
    }

    public TransferDto(Long idTransfer, Integer fees, BigDecimal feesAmount, BigDecimal transactionAmount, BigDecimal transferAmount, Customer recipient, Customer sender) {
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
