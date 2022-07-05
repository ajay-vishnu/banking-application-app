package com.ken.bankingapplication.Server;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ken.bankingapplication.Data.User;
import com.ken.bankingapplication.UI.ClientLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Authenticate {
    private static final String IP = "http://bankingapp.hopto.org";
    private User user = User.getUser();
    private static final Gson gson = new GsonBuilder().setLenient().create();
    private static final Retrofit retrofit = new Retrofit.Builder().baseUrl(IP).addConverterFactory(GsonConverterFactory.create(gson)).build();
    public static final API api = retrofit.create(API.class);


    public Authenticate(String name, String email, String password) {
        user.name = name;
        user.email = email;
        user.password = password;
        user.login = false;
        Call<ServerResponse> sendData = api.createUser(user);
        sendData.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response)    {
                assert response.body() != null;
                Log.d("banking-auth-success", "auth "+response.body().getAccountId());
                ClientLogin.accountID.setValue(response.body().getAccountId());
            }
            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t)   {
                Log.d("banking-auth-error", "auth "+t.getLocalizedMessage());
            }
        });
    }

    public Authenticate(String email, String password) {
        user.email = email;
        user.password = password;
        user.login = true;
        Call<ServerResponse> sendData = api.loginUser(user);
        sendData.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response)    {
                assert response.body() != null;
                user.name = response.body().getName();
                Log.d("banking-auth-suc", response.body().getAccountId() + " " + user.name);
                ClientLogin.accountID.setValue(response.body().getAccountId());
            }
            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, Throwable t)   {
                Log.d("banking-auth-err", t.getLocalizedMessage());
            }
        });
    }
}
