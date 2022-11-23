package com.coherent.store.http;

import com.sun.net.httpserver.BasicAuthenticator;

public class Authenticator extends BasicAuthenticator {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password";

    public Authenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return username.equals(USERNAME) && password.equals(PASSWORD);
    }
}