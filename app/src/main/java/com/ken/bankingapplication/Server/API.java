package com.ken.bankingapplication.Server;

import com.ken.bankingapplication.Data.AddTransaction;
import com.ken.bankingapplication.Data.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST("/bankingApp/user/createUser.php")
    Call<ServerResponse> createUser(@Body User user);
    @POST("/bankingApp/user/loginUser.php")
    Call<ServerResponse> loginUser(@Body User user);
    @POST("/bankingApp/activity/transactionList.php")
    Call<ServerResponse> getTransactionList(@Body User user);
    @POST("/bankingApp/activity/withdraw.php")
    Call<ServerResponse> withdrawMoney(@Body AddTransaction addTransaction);
    @POST("/bankingApp/activity/deposit.php")
    Call<ServerResponse> depositMoney(@Body AddTransaction addTransaction);
    @POST("/bankingApp/activity/transfer.php")
    Call<ServerResponse> transferMoney(@Body AddTransaction addTransaction);
}
