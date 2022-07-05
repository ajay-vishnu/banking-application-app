package com.ken.bankingapplication.Server;

import com.google.gson.annotations.SerializedName;

public class ServerResponse {
    @SerializedName("account_id")
    private String accountId;
    @SerializedName("name")
    private String name;
    @SerializedName("transaction_list")
    private String transactionList;
    @SerializedName("transaction_row")
    private String transactionRow;

    public String getAccountId() {
        return accountId;
    }

    public String getName() { return name; }

    public String getTransactionList() { return transactionList; }

    public String getTransactionRow() { return transactionRow; }
}
