package tests;

import com.nimi.api.common.JsonFilePaths;
import com.nimi.api.requests.nimikash.admin.AdminLogin;
import com.nimi.api.requests.nimikash.admin.AdminRegister;
import com.nimi.api.utility.JsonReaderUtil;
import com.nimi.ui.common.EmployeeData;
import com.nimi.ui.common.ProfileData;
import com.nimi.ui.functions.EmployeesFunctions;
import com.nimi.ui.functions.LoginFunctions;
import com.nimi.ui.functions.ProfileFunctions;
import com.nimi.ui.pages.LoginPage;
import com.nimi.ui.pages.Pages;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminUITests extends UITestBase{
    AdminLogin adminLogin = new AdminLogin();
    JsonReaderUtil jsonReaderUtil = new JsonReaderUtil();
    SoftAssert softAssert = new SoftAssert();
    AdminRegister adminRegister = new AdminRegister();

    @BeforeClass
    public void dataSetup(){
        adminLogin = jsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_ADMIN_LOGIN, AdminLogin.class);
        adminRegister = jsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_ADMIN_REGISTER, AdminRegister.class);
        LoginFunctions.adminLogin(UIDataSetupTests.newEmail, adminLogin.getPassword());
    }
    @Test(priority = 1)
    public void updateCompanyDetails(){
        Pages.loginPage().clickOnProfile();
        String userName = Pages.profilePage().getUserName();
        softAssert.assertEquals(userName, adminRegister.getName());
        softAssert.assertEquals(ProfileData.companyDetails, Pages.profilePage().getCompanyDetailsTitle());
        Pages.profilePage().clickOnCompanyDetailsEdit();
        softAssert.assertEquals(ProfileData.editCompanyProfile, Pages.profilePage().getEditCompanyProfileTitle());
        ProfileFunctions.editCompanyProfile(ProfileData.address01, ProfileData.address01, ProfileData.city, ProfileData.country, ProfileData.email, ProfileData.mobileNumber);
        softAssert.assertAll();
    }
    @Test(priority = 2)
    public void inviteAnEmployee(){
        Pages.employeesPage().clickOnEmployeesTab();
        Pages.employeesPage().clickOnInviteAnEmployee();
        String inviteEmployee = Pages.employeesPage().getInviteEmployee();
        String email = EmployeesFunctions.createNewEmpEmail();
        softAssert.assertEquals(inviteEmployee, EmployeeData.inviteEmployee);
        EmployeesFunctions.inviteEmployee(EmployeesFunctions.createEmpId(), email, EmployeeData.designation);
        Pages.employeesPage().clickOnInvitedTab();
        Pages.employeesPage().clickOnEdit(email);
        String editEmployee = Pages.employeesPage().getEditEmployee();
        softAssert.assertEquals(editEmployee, EmployeeData.editEmployee);
        softAssert.assertAll();
    }

    @AfterClass
    public void tearDown(){
        Pages.loginPage().closeBrowser();
    }

}

