package com.ken.bankingapplication.Misc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ken.bankingapplication.Data.Repository;
import com.ken.bankingapplication.Data.Transaction;
import com.ken.bankingapplication.R;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionDetails> {

    public interface TransactionCardSelect {
        void onCardClick(int positionOfTransactionCard);
    }

    TransactionCardSelect transactionCardSelect;
    Context context;
    Repository repo = Repository.getTransactions();

    public TransactionAdapter(Context context, TransactionCardSelect transactionCardSelect) {
        this.context = context;
        this.transactionCardSelect = transactionCardSelect;
    }

    @NonNull
    @Override
    public TransactionDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction_card, parent, false);
        return new TransactionDetails(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionDetails holder, int position) {
        Transaction t = repo.transactionList.get(position);
        holder.txtTransactionType.setText(t.getTransactionType());
        holder.txtTransactionTypeExpanded.setText(t.getTransactionType());
        holder.txtTransactionDate.setText(DateUtil.toString(t.getTransactionDate()));
        holder.txtTransactionId.setText(t.getTransactionId());
        holder.txtAccountBalance.setText(t.getAccountBalance());
        if (t.getTransactionType().equals("Received"))  {
            holder.txtTransferType.setText("Received From:");
            holder.txtTransferName.setText(t.getTransferName());
        }
        else if (t.getTransactionType().equals("Sent")) {
            holder.txtTransferType.setText("Sent To:");
            holder.txtTransferName.setText(t.getTransferName());
        }
        else    {
            holder.txtTransferType.setText("Sent/Received:");
            holder.txtTransferName.setText("-");
        }
        if (t.getTransactionType().equals("Deposit") || t.getTransactionType().equals("Received"))  {
            holder.txtTransactionAmount.setText(String.format("+%s", t.getTransactionAmount()));
            holder.txtTransactionAmount.setTextColor(holder.view.getResources().getColor(R.color.green));
            holder.txtTransactionAmountExpanded.setText(String.format("+%s", t.getTransactionAmount()));
            holder.txtTransactionAmountExpanded.setTextColor(holder.view.getResources().getColor(R.color.green));
        }
        else    {
            holder.txtTransactionAmount.setText(String.format("-%s", t.getTransactionAmount()));
            holder.txtTransactionAmount.setTextColor(holder.view.getResources().getColor(R.color.red));
            holder.txtTransactionAmountExpanded.setText(String.format("-%s", t.getTransactionAmount()));
            holder.txtTransactionAmountExpanded.setTextColor(holder.view.getResources().getColor(R.color.red));
        }
        if (t.getTransactionMessage().equals(""))   {
            holder.txtTransactionMessage.setText("-");
            holder.txtTransactionMessageExpanded.setText("-");
        }
        else    {
            holder.txtTransactionMessage.setText(t.getTransactionMessage().length() > 15 ? String.format("%.15s...", t.getTransactionMessage()) : t.getTransactionMessage());
            holder.txtTransactionMessageExpanded.setText(t.getTransactionMessage());
        }
        if (t.isExpanded)   {
            holder.crdTransactionCardExpanded.setVisibility(View.VISIBLE);
            holder.crdTransactionCard.setVisibility(View.GONE);
        }
        else    {
            holder.crdTransactionCardExpanded.setVisibility(View.GONE);
            holder.crdTransactionCard.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionDetails holder, int position, @NonNull List<Object> payloads) {
        if(payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads);
        else    {
            Bundle bundle = (Bundle) payloads.get(0);
            for (String key : bundle.keySet())  {
                if (key.equals("isExpanded"))   {
                    if (repo.transactionList.get(position).isExpanded)  {
                        holder.crdTransactionCard.setVisibility(View.GONE);
                        holder.crdTransactionCardExpanded.setVisibility(View.VISIBLE);
                    }
                    else    {
                        holder.crdTransactionCardExpanded.setVisibility(View.GONE);
                        holder.crdTransactionCard.setVisibility(View.VISIBLE);
                    }
                }
                if (key.equals("transactionId"))   {
                    holder.txtTransactionId.setText(repo.transactionList.get(position).getTransactionId());
                }
                if (key.equals("transactionType"))   {
                    holder.txtTransactionType.setText(repo.transactionList.get(position).getTransactionId());
                    holder.txtTransactionTypeExpanded.setText(repo.transactionList.get(position).getTransactionId());
                    if (repo.transactionList.get(position).getTransactionType().equals("Withdraw") || repo.transactionList.get(position).getTransactionType().equals("Deposit")) {
                        holder.txtTransferType.setText("Sent/Received:");
                        holder.txtTransferName.setText("-");
                    }
                    else if (repo.transactionList.get(position).getTransactionType().equals("Sent")) {
                        holder.txtTransferType.setText("Sent To:");
                        holder.txtTransferName.setText(repo.transactionList.get(position).getTransferName());
                    }
                    else if (repo.transactionList.get(position).getTransactionType().equals("Received"))    {
                        holder.txtTransferType.setText("Received From:");
                        holder.txtTransferName.setText(repo.transactionList.get(position).getTransferName());
                    }
                }
                if (key.equals("transactionAmount"))   {
                    if (repo.transactionList.get(position).getTransactionType().equals("Withdraw") || repo.transactionList.get(position).getTransactionType().equals("Received"))   {
                        holder.txtTransactionAmount.setText(String.format("+%s", repo.transactionList.get(position).getTransactionAmount()));
                        holder.txtTransactionAmount.setTextColor(holder.view.getResources().getColor(R.color.green));
                        holder.txtTransactionAmountExpanded.setText(String.format("+%s", repo.transactionList.get(position).getTransactionAmount()));
                        holder.txtTransactionAmountExpanded.setTextColor(holder.view.getResources().getColor(R.color.green));
                    }
                    else {
                        holder.txtTransactionAmount.setText(String.format("-%s", repo.transactionList.get(position).getTransactionAmount()));
                        holder.txtTransactionAmount.setTextColor(holder.view.getResources().getColor(R.color.red));
                        holder.txtTransactionAmountExpanded.setText(String.format("-%s", repo.transactionList.get(position).getTransactionAmount()));
                        holder.txtTransactionAmountExpanded.setTextColor(holder.view.getResources().getColor(R.color.red));
                    }
                }
                if (key.equals("transactionMessage"))   {
                    holder.txtTransactionMessage.setText(repo.transactionList.get(position).getTransactionMessage().length() > 15 ? String.format("%.15s...", repo.transactionList.get(position).getTransactionMessage()) : repo.transactionList.get(position).getTransactionMessage());
                    holder.txtTransactionMessageExpanded.setText(repo.transactionList.get(position).getTransactionMessage());
                }
                if (key.equals("transactionDate"))   {
                    holder.txtTransactionDate.setText(DateUtil.toString(repo.transactionList.get(position).getTransactionDate()));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return repo.transactionList.size();
    }

    class TransactionDetails extends RecyclerView.ViewHolder    {

        CardView crdTransactionCard, crdTransactionCardExpanded;
        TextView txtTransactionType, txtTransactionMessage, txtTransactionAmount, txtTransactionDate;
        TextView txtTransactionId, txtTransferName, txtTransferType, txtAccountBalance, txtMessageTitle;
        TextView txtTransactionTypeExpanded, txtTransactionMessageExpanded, txtTransactionAmountExpanded;
        View view;

        public TransactionDetails(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            crdTransactionCard = itemView.findViewById(R.id.transactionCard);
            crdTransactionCardExpanded = itemView.findViewById(R.id.transactionCardExpanded);
            txtTransactionType = itemView.findViewById(R.id.transactionType);
            txtTransactionTypeExpanded = itemView.findViewById(R.id.transactionTypeExpanded);
            txtTransactionMessage = itemView.findViewById(R.id.transactionMessage);
            txtTransactionMessageExpanded = itemView.findViewById(R.id.transactionMessageExpanded);
            txtTransactionAmount = itemView.findViewById(R.id.transactionAmount);
            txtTransactionAmountExpanded = itemView.findViewById(R.id.transactionAmountExpanded);
            txtTransactionDate = itemView.findViewById(R.id.transactionDate);
            txtTransactionId = itemView.findViewById(R.id.transactionId);
            txtTransferName = itemView.findViewById(R.id.transferName);
            txtTransferType = itemView.findViewById(R.id.transferType);
            txtAccountBalance = itemView.findViewById(R.id.balanceAccount);
            txtMessageTitle = itemView.findViewById(R.id.messageTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransactionAdapter.this.transactionCardSelect.onCardClick(getAdapterPosition());
                }
            });
        }
    }

    public void updateTransactions()    {
        TransactionDiffUtil diffUtil = new TransactionDiffUtil(repo.transactionList, repo.newTransactionList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtil);
        repo.update();
        diffResult.dispatchUpdatesTo(this);
    }
}
