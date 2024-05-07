package com.MoneyTransferSystem.dto.request;

public class TransactionRequest {
    private Long senderAccountId;
    private Long receiverAccountId;
    private Long amount;

    public Long getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Long senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Long getReceiverAccountId() {
        return receiverAccountId;
    }

    public void setReceiverAccountId(Long receiverAccountId) {
        this.receiverAccountId = receiverAccountId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}