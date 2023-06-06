package com.nimi.ui.functions;

import com.nimi.ui.pages.Pages;
import com.nimi.ui.utility.NumberUtil;

import java.util.Random;

public class EmployeesFunctions {

    public static String createNewEmpEmail() {
        String empEmail = NumberUtil.generateRandomNo() + "emptest@gmail.com";
        return empEmail;
    }
    public static String createEmpId() {
        String empId = "EID-"+ NumberUtil.generateRandomNo();
        return empId;
    }
    public static void inviteEmployee(String employeeID, String email, String designation) {
        Pages.employeesPage().enterEmployeeID(employeeID);
        Pages.employeesPage().enterEmail(email);
        Pages.employeesPage().enterDesignation(designation);
        Pages.employeesPage().clickOnCurrencyDropdown();
        Pages.employeesPage().clickOnCurrency();
        Pages.employeesPage().clickOnPlus();
        Pages.employeesPage().clickOnInvite();
    }
}
