package com.nimi.ui.functions;

import com.nimi.ui.pages.Pages;

public class LoginFunctions {

    public static void adminLogin(String email, String password){
        Pages.loginPage().enterEmail(email);
        Pages.loginPage().enterPassword(password);
        Pages.loginPage().clickOnLogin();
    }
}
