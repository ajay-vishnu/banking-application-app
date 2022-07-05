package com.ken.bankingapplication.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ken.bankingapplication.Data.User;
import com.ken.bankingapplication.Misc.ErrorHandling;
import com.ken.bankingapplication.R;
import com.ken.bankingapplication.Server.Authenticate;

public class ClientLogin extends AppCompatActivity {

    public static final String TAG = "banking-cloud-client";
    private TextInputEditText edtName, edtEmail, edtPassword, edtConfirmPassword;
    private TextInputLayout layConfirm, layName;
    private TextView txtLogin, txtSignup, txtPasswordForgot;
    private Button submit;
    private User user = User.getUser();
    private boolean login = true;
    public static MutableLiveData<String> accountID = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        accountID.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                user.accountId = s;
                openClientDashboard();
            }
        });

        txtLogin = findViewById(R.id.textLogin);
        txtSignup = findViewById(R.id.textSignup);
        txtPasswordForgot = findViewById(R.id.textForgotPassword);
        submit = findViewById(R.id.btnSubmit);
        layConfirm = findViewById(R.id.layConfirmPassword);
        layName = findViewById(R.id.layName);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        Log.d(TAG, token);
                        Toast.makeText(ClientLogin.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtLogin.setBackground(getResources().getDrawable(R.drawable.text_selected));
                txtLogin.setTextColor(getResources().getColor(R.color.black));
                txtLogin.setElevation(4);
                txtSignup.setElevation(0);
                txtSignup.setBackground(null);
                txtSignup.setTextColor(getResources().getColor(R.color.blue));
                submit.setText("Log in");
                txtPasswordForgot.setVisibility(View.VISIBLE);
                layConfirm.setVisibility(View.GONE);
                layName.setVisibility(View.GONE);
                login = true;
            }
        });

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSignup.setBackground(getResources().getDrawable(R.drawable.text_selected));
                txtSignup.setTextColor(getResources().getColor(R.color.black));
                txtSignup.setElevation(4);
                txtLogin.setElevation(0);
                txtLogin.setBackground(null);
                txtLogin.setTextColor(getResources().getColor(R.color.blue));
                submit.setText("Sign up");
                txtPasswordForgot.setVisibility(View.GONE);
                layConfirm.setVisibility(View.VISIBLE);
                layName.setVisibility(View.VISIBLE);
                login = false;
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login)  {
                    String email = edtEmail.getText().toString(), password = edtPassword.getText().toString();
                    if (ErrorHandling.login(email, password))   {
                        new Authenticate(email, password);
                    }
                    else    {
                        Toast.makeText(ClientLogin.this, "Enter valid fields", Toast.LENGTH_LONG).show();
                    }
                }
                else    {
                    String name = edtName.getText().toString(), email = edtEmail.getText().toString(), password = edtPassword.getText().toString(), confirmPassword = edtConfirmPassword.getText().toString();
                    if (ErrorHandling.signup(name, email, password, confirmPassword))   {
                        new Authenticate(edtName.getText().toString(),edtEmail.getText().toString(), edtPassword.getText().toString());
                    }
                    else    {
                        Toast.makeText(ClientLogin.this, "Enter valid fields", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void openClientDashboard()    {
        Intent intent = new Intent(this, ClientDashboard.class);
        startActivity(intent);
    }
}