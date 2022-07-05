package com.ken.bankingapplication.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ken.bankingapplication.Data.AddTransaction;
import com.ken.bankingapplication.Data.Repository;
import com.ken.bankingapplication.Data.Transaction;
import com.ken.bankingapplication.Data.User;
import com.ken.bankingapplication.UI.ClientDashboard;

import java.util.StringTokenizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticateTransaction {
    private static String transactionList;
    private static API api = Authenticate.api;
    private static Repository repo = Repository.getTransactions();

    public static void loadTransactions() {
        Log.d("banking-tran", "Entered transactions");
        Call<ServerResponse> sendData = api.getTransactionList(User.getUser());
        sendData.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, Response<ServerResponse> response) {
                int count = 0;
                assert response.body() != null;
                transactionList = response.body().getTransactionList();
                Log.d("banking-tran-success", "Response: " + transactionList);
                StringTokenizer st = new StringTokenizer(transactionList, "#");
                while(st.hasMoreTokens())   {
                    boolean unique = true;
                    StringTokenizer st1 = new StringTokenizer(st.nextToken(), "$");
                    String transactionID = st1.nextToken();
                    for (Transaction t : repo.newTransactionList)  {
                        if (transactionID.equals(t.getTransactionId())) {
                            unique = false;
                        }
                    }
                    if (unique) {
                        count++;
                        String transactionDate = st1.nextToken();
                        String transactionType = st1.nextToken();
                        String transferName = st1.nextToken();
                        transferName = transferName.equals("!!!!") ? "" : transferName;
                        String transactionAmount = st1.nextToken();
                        String accountBalance = st1.nextToken();
                        String transactionMessage = st1.nextToken();
                        transactionMessage = transactionMessage.equals("!!!!") ? "" : transactionMessage;
                        repo.newTransactionList.add(new Transaction(transactionType, transactionAmount, accountBalance, transactionID, transactionMessage, transferName, transactionDate));
                    }
                }
                if (ClientDashboard.transactionCount.getValue() == null || (ClientDashboard.transactionCount.getValue() != null && count != ClientDashboard.transactionCount.getValue()))   {
                    ClientDashboard.transactionCount.setValue(count);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Log.d("banking-tran-err", "trans "+t.getLocalizedMessage());
            }
        });
    }

    public static void withdrawMoney(String amount, String message)   {
        Call<ServerResponse> sendData = api.withdrawMoney(new AddTransaction(amount, message));
        sendData.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                assert response.body() != null;
                transactionList = response.body().getTransactionRow();
                Log.d("banking-with-suc", transactionList);
                if (!transactionList.equals("-1"))  {
                    StringTokenizer st = new StringTokenizer(transactionList, "#");
                    while(st.hasMoreTokens())   {
                        boolean unique = true;
                        StringTokenizer st1 = new StringTokenizer(st.nextToken(), "$");
                        String transactionID = st1.nextToken();
                        for (Transaction t : repo.newTransactionList)  {
                            if (transactionID.equals(t.getTransactionId())) {
                                unique = false;
                            }
                        }
                        if (unique) {
                            String transactionDate = st1.nextToken();
                            String transactionType = st1.nextToken();
                            String transferName = st1.nextToken();
                            transferName = transferName.equals("!!!!") ? "" : transferName;
                            String transactionAmount = st1.nextToken();
                            String accountBalance = st1.nextToken();
                            String transactionMessage = st1.nextToken();
                            transactionMessage = transactionMessage.equals("!!!!") ? "" : transactionMessage;
                            repo.newTransactionList.add(new Transaction(transactionType, transactionAmount, accountBalance, transactionID, transactionMessage, transferName, transactionDate));
                        }
                    }
                    int count = ClientDashboard.transactionCount.getValue();
                    ClientDashboard.transactionCount.setValue(++count);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("banking-with-err", "trans "+t.getLocalizedMessage());
            }
        });
    }

    public static void depositMoney(String amount, String message)   {
        Call<ServerResponse> sendData = api.depositMoney(new AddTransaction(amount, message));
        sendData.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                assert response.body() != null;
                transactionList = response.body().getTransactionRow();
                Log.d("banking-depo-suc", transactionList);
                if (!transactionList.equals("-1"))  {
                    StringTokenizer st = new StringTokenizer(transactionList, "#");
                    while(st.hasMoreTokens())   {
                        boolean unique = true;
                        StringTokenizer st1 = new StringTokenizer(st.nextToken(), "$");
                        String transactionID = st1.nextToken();
                        for (Transaction t : repo.newTransactionList)  {
                            if (transactionID.equals(t.getTransactionId())) {
                                unique = false;
                            }
                        }
                        if (unique) {
                            String transactionDate = st1.nextToken();
                            String transactionType = st1.nextToken();
                            String transferName = st1.nextToken();
                            transferName = transferName.equals("!!!!") ? "" : transferName;
                            String transactionAmount = st1.nextToken();
                            String accountBalance = st1.nextToken();
                            String transactionMessage = st1.nextToken();
                            transactionMessage = transactionMessage.equals("!!!!") ? "" : transactionMessage;
                            repo.newTransactionList.add(new Transaction(transactionType, transactionAmount, accountBalance, transactionID, transactionMessage, transferName, transactionDate));
                        }
                    }
                    int count = ClientDashboard.transactionCount.getValue();
                    ClientDashboard.transactionCount.setValue(++count);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("banking-depo-err", "trans "+t.getLocalizedMessage());
            }
        });
    }

    public static void sendMoney(String amount, String message, String transferId, String transferName)   {
        Call<ServerResponse> sendData = api.transferMoney(new AddTransaction(amount, message, transferId, transferName));
        sendData.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                assert response.body() != null;
                transactionList = response.body().getTransactionRow();
                Log.d("banking-send-suc", transactionList);
                if (!transactionList.equals("-1"))  {
                    StringTokenizer st = new StringTokenizer(transactionList, "#");
                    while(st.hasMoreTokens())   {
                        boolean unique = true;
                        StringTokenizer st1 = new StringTokenizer(st.nextToken(), "$");
                        String transactionID = st1.nextToken();
                        for (Transaction t : repo.newTransactionList)  {
                            if (transactionID.equals(t.getTransactionId())) {
                                unique = false;
                            }
                        }
                        if (unique) {
                            String transactionDate = st1.nextToken();
                            String transactionType = st1.nextToken();
                            String transferName = st1.nextToken();
                            transferName = transferName.equals("!!!!") ? "" : transferName;
                            String transactionAmount = st1.nextToken();
                            String accountBalance = st1.nextToken();
                            String transactionMessage = st1.nextToken();
                            transactionMessage = transactionMessage.equals("!!!!") ? "" : transactionMessage;
                            repo.newTransactionList.add(new Transaction(transactionType, transactionAmount, accountBalance, transactionID, transactionMessage, transferName, transactionDate));
                        }
                    }
                    int count = ClientDashboard.transactionCount.getValue();
                    ClientDashboard.transactionCount.setValue(++count);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("banking-send-err", "trans "+t.getLocalizedMessage());
            }
        });
    }
}
