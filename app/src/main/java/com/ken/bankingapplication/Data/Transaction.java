package com.ken.bankingapplication.Data;

import com.ken.bankingapplication.Misc.DateUtil;

import java.util.Date;

public class Transaction {
    String transactionType;
    String transactionAmount;
    String transactionId;
    String transactionMessage;
    String transferName;
    String accountBalance;
    Date transactionDate;
    public boolean isExpanded = false;

    public Transaction(String transactionType, String transactionAmount, String accountBalance, String transactionId, String transactionMessage, String transferName, String transactionDate) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.accountBalance = accountBalance;
        this.transactionId = transactionId;
        this.transactionMessage = transactionMessage;
        this.transferName = transferName;
        this.transactionDate = DateUtil.toDate(transactionDate);
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionMessage() {
        return transactionMessage;
    }

    public String getTransferName() {
        return transferName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getAccountBalance() { return accountBalance; }
}
