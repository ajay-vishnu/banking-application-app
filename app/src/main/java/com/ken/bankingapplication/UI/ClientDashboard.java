package com.ken.bankingapplication.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.ken.bankingapplication.Data.Repository;
import com.ken.bankingapplication.Data.User;
import com.ken.bankingapplication.Misc.TransactionAdapter;
import com.ken.bankingapplication.R;
import com.ken.bankingapplication.Server.AuthenticateTransaction;

public class ClientDashboard extends AppCompatActivity implements TransactionAdapter.TransactionCardSelect {

    private RecyclerView transactionView;
    private MaterialCardView crdWithdrawActivity, crdDepositActivity, crdSendActivity;
    private TextView txtWithdrawActivity, txtDepositActivity, txtSendActivity;
    private LinearLayout layReceiverDetails;
    private TextInputEditText edtAmount, edtMessage, edtReceiverName, edtReceiverAccountId;
    private Button btnSubmit;
    private String currentActivity = "Send";
    public static MutableLiveData<Integer> transactionCount = new MutableLiveData<>();
    private User user = User.getUser();
    Repository repo = Repository.getTransactions();
    TransactionAdapter transactionAdapter = new TransactionAdapter(ClientDashboard.this, this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_dashboard);

        transactionCount.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                user.transactionCount = integer;
                transactionAdapter.updateTransactions();
            }
        });

        AuthenticateTransaction.loadTransactions();

        transactionView = findViewById(R.id.transactionList);
        crdWithdrawActivity = findViewById(R.id.activityWithdrawButton);
        crdDepositActivity = findViewById(R.id.activityDepositButton);
        crdSendActivity = findViewById(R.id.activitySendButton);
        txtWithdrawActivity = findViewById(R.id.activityWithdrawText);
        txtDepositActivity = findViewById(R.id.activityDepositText);
        txtSendActivity = findViewById(R.id.activitySendText);
        layReceiverDetails = findViewById(R.id.linearReceiverDetails);
        edtReceiverName = findViewById(R.id.edtName);
        edtReceiverAccountId = findViewById(R.id.edtTransactionId);
        edtAmount = findViewById(R.id.edtAmount);
        edtMessage = findViewById(R.id.edtMessage);
        btnSubmit = findViewById(R.id.btnSubmit);

        inflateTransactions();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentActivity.equals("Withdraw")) {
                    String amount = edtAmount.getText().toString();
                    String message = edtMessage.getText().toString();
                    if (amount.isEmpty())   {
                        Toast.makeText(ClientDashboard.this, "Enter Valid amount", Toast.LENGTH_LONG).show();
                    }
                    else    {
                        AuthenticateTransaction.withdrawMoney(amount, message);
                    }
                }
                else if (currentActivity.equals("Deposit")) {
                    String amount = edtAmount.getText().toString();
                    String message = edtMessage.getText().toString();
                    if (amount.isEmpty())   {
                        Toast.makeText(ClientDashboard.this, "Enter Valid amount", Toast.LENGTH_LONG).show();
                    }
                    else    {
                        AuthenticateTransaction.depositMoney(amount, message);
                    }
                }
                else {
                    String amount = edtAmount.getText().toString();
                    String message = edtMessage.getText().toString();
                    String name = edtReceiverName.getText().toString();
                    String accountId = edtReceiverAccountId.getText().toString();
                    if (amount.isEmpty() || name.isEmpty() || accountId.isEmpty())  {
                        Toast.makeText(ClientDashboard.this, "Enter Valid amount", Toast.LENGTH_LONG).show();
                    }
                    else    {
                        AuthenticateTransaction.sendMoney(amount, message, accountId, name);
                    }
                }
            }
        });

        crdWithdrawActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentActivity = "Withdraw";
                btnSubmit.setText("Withdraw");
                layReceiverDetails.setVisibility(View.GONE);
                edtAmount.setTextSize(20);
                edtMessage.setTextSize(20);
                crdWithdrawActivity.setStrokeColor(getResources().getColor(R.color.light_grey));
                crdWithdrawActivity.setCardBackgroundColor(getResources().getColor(R.color.blue));
                txtWithdrawActivity.setTextColor(getResources().getColor(R.color.light_grey));
                crdDepositActivity.setStrokeColor(getResources().getColor(R.color.blue));
                crdDepositActivity.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
                txtDepositActivity.setTextColor(getResources().getColor(R.color.blue));
                crdSendActivity.setStrokeColor(getResources().getColor(R.color.blue));
                crdSendActivity.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
                txtSendActivity.setTextColor(getResources().getColor(R.color.blue));
            }
        });

        crdDepositActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentActivity = "Deposit";
                btnSubmit.setText("Deposit");
                layReceiverDetails.setVisibility(View.GONE);
                edtAmount.setTextSize(20);
                edtMessage.setTextSize(20);
                crdWithdrawActivity.setStrokeColor(getResources().getColor(R.color.blue));
                crdWithdrawActivity.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
                txtWithdrawActivity.setTextColor(getResources().getColor(R.color.blue));
                crdDepositActivity.setStrokeColor(getResources().getColor(R.color.light_grey));
                crdDepositActivity.setCardBackgroundColor(getResources().getColor(R.color.blue));
                txtDepositActivity.setTextColor(getResources().getColor(R.color.light_grey));
                crdSendActivity.setStrokeColor(getResources().getColor(R.color.blue));
                crdSendActivity.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
                txtSendActivity.setTextColor(getResources().getColor(R.color.blue));
            }
        });

        crdSendActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentActivity = "Send";
                btnSubmit.setText("Send");
                layReceiverDetails.setVisibility(View.VISIBLE);
                edtAmount.setTextSize(12);
                edtMessage.setTextSize(12);
                crdWithdrawActivity.setStrokeColor(getResources().getColor(R.color.blue));
                crdWithdrawActivity.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
                txtWithdrawActivity.setTextColor(getResources().getColor(R.color.blue));
                crdDepositActivity.setStrokeColor(getResources().getColor(R.color.blue));
                crdDepositActivity.setCardBackgroundColor(getResources().getColor(R.color.light_grey));
                txtDepositActivity.setTextColor(getResources().getColor(R.color.blue));
                crdSendActivity.setStrokeColor(getResources().getColor(R.color.light_grey));
                crdSendActivity.setCardBackgroundColor(getResources().getColor(R.color.blue));
                txtSendActivity.setTextColor(getResources().getColor(R.color.light_grey));
            }
        });

    }

    private void inflateTransactions()  {
        transactionView.setLayoutManager(new LinearLayoutManager(ClientDashboard.this));
        transactionView.setAdapter(transactionAdapter);
    }

    @Override
    public void onCardClick(int positionOfTransactionCard) {
        if (repo.currentExpanded != -1) {
            repo.newTransactionList.get(repo.currentExpanded).isExpanded = false;
        }
        if (repo.currentExpanded != positionOfTransactionCard)  {
            repo.currentExpanded = positionOfTransactionCard;
            repo.newTransactionList.get(positionOfTransactionCard).isExpanded = true;
        }
        else    {
            repo.currentExpanded = -1;
        }
        transactionAdapter.updateTransactions();
    }
}