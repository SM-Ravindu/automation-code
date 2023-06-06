package com.nimi.ui.pages;

import com.nimi.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class EmployeesPage {

    static By btnEmployeesTab = By.xpath("//p[contains(text(),'Employees')]");
    static By btnInviteAnEmployee = By.xpath("//button[contains(text(),\"Invite an Employee\")]");
    static By txtInviteEmployee = By.id("modal-modal-title");
    static By txtEmployeeID = By.xpath("//input[@placeholder='Employee ID']");
    static By txtEmail = By.xpath("//input[@placeholder='Email']");
    static By txtDesignation = By.xpath("//input[@placeholder='Designation']");
    static By eleCurrencyDropdown = By.xpath("//input[@placeholder='Designation']/../../../div[4]");
    static By txtCurrency = By.xpath("//li[contains(text(),\"United States Dollar\")]");
    static By btnPlus = By.xpath("//input[@placeholder='Designation']/../../../button/*[name()='svg']");
    static By btnInvite = By.xpath("//button[normalize-space()='Invite']");
    static By btnInvitedTab = By.xpath("//button[@id='simple-tab-2']");
    static By txtEditEmployee = By.xpath("//p[contains(text(),'Edit Employee')]");

    public static void clickOnEmployeesTab(){
        Browser.click(btnEmployeesTab);
    }
    public static void clickOnInviteAnEmployee(){
        Browser.click(btnInviteAnEmployee);
    }
    public static String getInviteEmployee(){
        Browser.waitTillElementLocated(txtInviteEmployee, Duration.ofSeconds(3));
        return Browser.getText(txtInviteEmployee);
    }
    public static void enterEmployeeID(String employeeID){
        Browser.clear(txtEmployeeID);
        Browser.type(txtEmployeeID, employeeID);
    }
    public static void enterEmail(String email){
        Browser.clear(txtEmail);
        Browser.type(txtEmail, email);
    }
    public static void enterDesignation(String designation){
        Browser.clear(txtDesignation);
        Browser.type(txtDesignation, designation);
    }
//    public static void clickOnCurrencyDropdown(){
//        Browser.click(eleCurrencyDropdown);
//        List<WebElement> options = Browser.findElements(By.cssSelector("ul.MuiList-root.MuiList-padding.MuiMenu-list.css-r8u8y9>li"));
//        for (WebElement webElement : options) {
//            if(webElement.getText().trim().equals("Australian Dollar")) {
//                webElement.click();
//                break;
//            }
//        }
//    }
    public static void clickOnCurrencyDropdown(){
        Browser.click(eleCurrencyDropdown);
    }
    public static void clickOnCurrency(){
        Browser.click(txtCurrency);
    }
    public static void clickOnPlus(){
        Browser.click(btnPlus);
    }
    public static void clickOnInvite(){
        Browser.click(btnInvite);
    }
    public static void clickOnInvitedTab(){
        Browser.click(btnInvitedTab);
    }
    public static void clickOnEdit(String email){
        By btnEdit = By.xpath("//td[contains(text(),'"+email+"')]/../td[3]/button[2]/div");
        Browser.click(btnEdit);
    }
    public static String getEditEmployee(){
        Browser.waitTillElementLocated(txtEditEmployee, Duration.ofSeconds(3));
        return Browser.getText(txtEditEmployee);
    }
}
