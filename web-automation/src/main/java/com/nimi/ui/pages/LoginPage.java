package com.nimi.ui.pages;

import com.nimi.Browser;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    static By txtEmail = By.name("email");
    static By txtPassword = By.name("password");
    static By btnLogin = By.xpath("//button[text()='Login']");
    static By lnkProfile = By.xpath("//p[text()='Profile']");
    public static void loadUrl(String url){
        Browser.wait(20, TimeUnit.SECONDS);
        Browser.maximizeWindow();
        Browser.goTo(url);
    }

    public static void enterEmail(String email){
        Browser.clear(txtEmail);
        Browser.type(txtEmail, email);
    }

    public static void enterPassword(String password){
        Browser.clear(txtPassword);
        Browser.type(txtPassword, password);
    }

    public static void clickOnLogin(){
        Browser.click(btnLogin);
    }

    public static void clickOnProfile(){
        Browser.click(lnkProfile);
    }

    public static void closeBrowser(){
        Browser.close();
    }
}
