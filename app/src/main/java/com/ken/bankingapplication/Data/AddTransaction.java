package com.ken.bankingapplication.Data;

public class AddTransaction {
    private String amount;
    private String accountId;
    private String transactionMessage;
    private String transferAccountId;
    private String transferName;

    public AddTransaction(String amount, String message) {
        accountId = User.getUser().accountId;
        this.amount = amount;
        this.transactionMessage = message;
    }

    public AddTransaction(String amount, String message, String transferAccountId, String transferName) {
        accountId = User.getUser().accountId;
        this.amount = amount;
        this.transactionMessage = message;
        this.transferAccountId = transferAccountId;
        this.transferName = transferName;
    }
}
