package com.nimi.ui.pages;

import com.nimi.Browser;
import org.openqa.selenium.By;

import java.time.Duration;

public class ProfilePage {

    static By txtUserName = By.xpath("//p[text()='User Profile']/../../div[2]/p[1]");

//    Company Details Edit
    static By txtCompanyDetails = By.xpath("//p[contains(text(),'Company Details')]");
    static By txtCompleteCompanyDetails = By.xpath("//div[contains(text(),'Please Complete company details to proceed with up')]");
    static By btnCompanyDetailsEdit = By.xpath("//p[contains(text(),'Company Details')]/../../div[2]/button");
    static By txtEditCompanyProfile = By.xpath("//p[contains(text(),'Edit Company Profile')]");
    static By btnCountryCodeDropdown = By.xpath("//div[@id='mui-component-select-code']");
    static By txtAddressLine01 = By.xpath("//div/input[@name=\"address_01\"]");
    static By txtAddressLine02 = By.xpath("//div/input[@name=\"address_02\"]");
    static By txtCity = By.xpath("//div/input[@name=\"city\"]");
    static By txtCountry = By.xpath("//div/input[@name=\"country\"]");
    static By txtSelectLK = By.xpath("//div/ul/li[contains(text(),\"LK (+94)\")]");
    static By txtEmail = By.xpath("//div/input[@name=\"email\"]");
    static By txtMobileNumber = By.xpath("//div//div//input[@name=\"number\"]");
    static By btnPayDay = By.xpath("//div[7]//button");
    static By txtSelectPayDay = By.xpath("//body/div[3]/div[3]//div//li[@value=\"20\"]");
    static By btnUpdate = By.xpath("//div/button[contains(text(),\"Update\")]");
    static By btnCancel = By.xpath("//div/button[contains(text(),\"Cancel\")]");

    public static String getUserName(){
        Browser.waitTillElementLocated(txtUserName, Duration.ofSeconds(3));
        return Browser.getText(txtUserName);
    }
    public static void clickOnCompanyDetailsEdit(){
        Browser.click(btnCompanyDetailsEdit);
    }
    public static String getCompanyDetailsTitle(){
        Browser.waitTillElementLocated(txtCompanyDetails, Duration.ofSeconds(3));
        return Browser.getText(txtCompanyDetails);
    }
    public static String getEditCompanyProfileTitle(){
        Browser.waitTillElementLocated(txtEditCompanyProfile, Duration.ofSeconds(3));
        return Browser.getText(txtEditCompanyProfile);
    }
    public static void clickOnCountryCodeDropdown(){
        Browser.click(btnCountryCodeDropdown);
    }
    public static void enterAddressLine01(String address01){
        Browser.clear(txtAddressLine01);
        Browser.type(txtAddressLine01, address01);
    }
    public static void enterAddressLine02(String address02){
        Browser.clear(txtAddressLine02);
        Browser.type(txtAddressLine02, address02);
    }
    public static void enterCity(String city){
        Browser.clear(txtCity);
        Browser.type(txtCity, city);
    }
    public static void enterCountry(String country){
        Browser.clear(txtCountry);
        Browser.type(txtCountry, country);
    }
    public static void enterEmail(String email){
        Browser.clear(txtEmail);
        Browser.type(txtEmail, email);
    }
    public static void enterMobileNumber(String mobileNumber){
        Browser.clear(txtMobileNumber);
        Browser.type(txtMobileNumber, mobileNumber);
    }
    public static void clickOnSelectLK(){
        Browser.click(txtSelectLK);
    }
    public static void clickOnPayDay(){
        Browser.click(btnPayDay);
    }
    public static void clickOnSelectPayDay(){
        Browser.click(txtSelectPayDay);
    }
    public static void clickOnUpdate(){
        Browser.click(btnUpdate);
    }
    public static void clickOnCancel(){
        Browser.click(btnCancel);
    }

}
