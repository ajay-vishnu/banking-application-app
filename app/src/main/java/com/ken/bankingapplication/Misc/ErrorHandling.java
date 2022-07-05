package com.ken.bankingapplication.Misc;

import android.util.Log;

import com.ken.bankingapplication.Data.Repository;
import com.ken.bankingapplication.Data.Transaction;
import com.ken.bankingapplication.Server.Authenticate;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorHandling {

    private static String emailFormat = "^[a-z0-9\\.]+@gmail.com$";
    private static Pattern p = Pattern.compile(emailFormat);
    public static boolean login(String email, String password)  {
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }
        else    {
            Matcher m = p.matcher(email);
            if (m.find()) {
                return true;
            }
            else    {
                return false;
            }
        }
    }
    public static boolean signup(String name, String email, String password, String confirmPassword)    {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())   {
            return false;
        }
        else {
            Matcher m = p.matcher(email);
            Pattern p1 = Pattern.compile("^[\\w]+$");
            Matcher m1 = p1.matcher(name);
            boolean b1 = m.find(), b2 = m1.find(), b3 = password.equals(confirmPassword);
            Log.d("banking-params", String.format("%s %s %s %s %s %s %s", name, email, password, confirmPassword, b1, b2, b3));
            if (b1 && b2 && b3)   {
                return true;
            }
            else    {
                return false;
            }
        }
    }
}
