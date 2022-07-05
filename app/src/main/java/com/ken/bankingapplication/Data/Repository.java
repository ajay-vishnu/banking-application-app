package com.ken.bankingapplication.Data;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static Repository transactions;
    public int currentExpanded = -1;
    public ArrayList<Transaction> transactionList = new ArrayList();
    public ArrayList<Transaction> newTransactionList = new ArrayList();

    private Repository()    {

    }

    public static Repository getTransactions() {
        if (transactions == null)   {
            transactions = new Repository();
        }
        return transactions;
    }

    public void update() {
        transactionList.clear();
        transactionList.addAll(newTransactionList);
    }
}
