package com.ken.bankingapplication.Data;

public class User {
    public String accountId;
    public Integer transactionCount;
    public String name;
    public String email;
    public String password;
    public String receiverAccountId;
    public String amount;
    public boolean login;
    private static User user;

    private User()  {

    }

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

}
