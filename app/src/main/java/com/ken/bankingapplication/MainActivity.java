package com.ken.bankingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ken.bankingapplication.R;
import com.ken.bankingapplication.UI.AdminLogin;
import com.ken.bankingapplication.UI.ClientLogin;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openClientActivity(View view)    {
        Intent intent = new Intent(this, ClientLogin.class);
        startActivity(intent);
    }

    public void openAdminActivity(View view) {
        Intent intent = new Intent(this, AdminLogin.class);
        startActivity(intent);
    }
}