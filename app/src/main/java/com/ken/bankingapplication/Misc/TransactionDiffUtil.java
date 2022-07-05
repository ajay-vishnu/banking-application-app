package com.ken.bankingapplication.Misc;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.ken.bankingapplication.Data.Transaction;

import java.util.List;

public class TransactionDiffUtil extends DiffUtil.Callback {

    private List<Transaction> oldList, newList;

    public TransactionDiffUtil(List<Transaction> oldList, List<Transaction> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getTransactionId().equals(newList.get(newItemPosition).getTransactionId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (!oldList.get(oldItemPosition).getTransactionId().equals(newList.get(newItemPosition).getTransactionId()))   {
            return false;
        }

        else if (!oldList.get(oldItemPosition).getTransactionType().equals(newList.get(newItemPosition).getTransactionType()))   {
            return false;
        }

        else if (!oldList.get(oldItemPosition).getTransactionAmount().equals(newList.get(newItemPosition).getTransactionAmount()))   {
            return false;
        }

        else if (!oldList.get(oldItemPosition).getTransactionMessage().equals(newList.get(newItemPosition).getTransactionMessage()))   {
            return false;
        }

        else if (!oldList.get(oldItemPosition).getTransferName().equals(newList.get(newItemPosition).getTransferName()))   {
            return false;
        }

        else if (!oldList.get(oldItemPosition).getTransactionDate().equals(newList.get(newItemPosition).getTransactionDate()))   {
            return false;
        }

        else if (!oldList.get(oldItemPosition).getAccountBalance().equals(newList.get(newItemPosition).getAccountBalance()))   {
            return false;
        }

        else if (!(oldList.get(oldItemPosition).isExpanded == newList.get(newItemPosition).isExpanded)) {
            return false;
        }

        else {
            return false;
        }
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Transaction newTransaction = newList.get(newItemPosition);
        Transaction oldTransaction = oldList.get(oldItemPosition);

        Bundle bundle = new Bundle();

        if (!(newTransaction.isExpanded == oldTransaction.isExpanded))  {
            bundle.putString("isExpanded", String.format("%s",newTransaction.isExpanded));
        }
        if (!oldList.get(oldItemPosition).getTransactionId().equals(newList.get(newItemPosition).getTransactionId()))   {
            bundle.putString("transactionId", newList.get(newItemPosition).getTransactionId());
        }

        if (!oldList.get(oldItemPosition).getTransactionType().equals(newList.get(newItemPosition).getTransactionType()))   {
            bundle.putString("transactionType", newList.get(newItemPosition).getTransactionType());
        }

        if (!oldList.get(oldItemPosition).getTransactionAmount().equals(newList.get(newItemPosition).getTransactionAmount()))   {
            bundle.putString("transactionAmount", newList.get(newItemPosition).getTransactionAmount());
        }

        if (!oldList.get(oldItemPosition).getTransactionMessage().equals(newList.get(newItemPosition).getTransactionMessage()))   {
            bundle.putString("transactionMessage", newList.get(newItemPosition).getTransactionMessage());
        }

        if (!oldList.get(oldItemPosition).getTransferName().equals(newList.get(newItemPosition).getTransferName()))   {
            bundle.putString("transferName", newList.get(newItemPosition).getTransferName());
        }

        if (!oldList.get(oldItemPosition).getTransactionDate().equals(newList.get(newItemPosition).getTransactionDate()))   {
            bundle.putString("transactionDate", newList.get(newItemPosition).getTransactionDate().toString());
        }

        if (!oldList.get(oldItemPosition).getAccountBalance().equals(newList.get(newItemPosition).getAccountBalance()))   {bundle.putString("transactionId", newList.get(newItemPosition).getTransactionId());
            bundle.putString("transactionAmount", newList.get(newItemPosition).getAccountBalance().toString());
        }
        return bundle.size() == 0 ? null : bundle;
    }
}
