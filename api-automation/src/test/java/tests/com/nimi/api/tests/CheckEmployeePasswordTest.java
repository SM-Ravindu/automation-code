package tests.com.nimi.api.tests;

import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.data.EmployeeData;
import com.nimi.api.functions.AdminFunctions;
import com.nimi.api.functions.EmployeeFunctions;
import com.nimi.api.utility.ResponseUtil;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.nimi.api.data.InviteEmployeeData;
import com.nimi.api.utility.NumberUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckEmployeePasswordTest extends APITestBase {

    Response response;
    EmployeeData employeeData = new EmployeeData();
    String employeeAccessToken = null;
    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    public void validateWithValidEmailAndPassword(){
        employeeAccessToken = EmployeeFunctions.employeeLogin(properties, employeeData.email, employeeData.password);

        response = EmployeeFunctions.checkEmployeePassword(properties, employeeData.email, employeeData.password, employeeAccessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.MESSAGE),"valid password");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void validateWithInvalidEmail(){
        employeeAccessToken = EmployeeFunctions.employeeLogin(properties, employeeData.email, employeeData.password);
        System.out.println("Access Token ; "+employeeAccessToken);

        response = EmployeeFunctions.checkEmployeePassword(properties, employeeData.invalidEmail, employeeData.password, employeeAccessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"permission denied");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void validateWithIncorrectPassword(){
        employeeAccessToken = EmployeeFunctions.employeeLogin(properties, employeeData.email, employeeData.password);
        System.out.println("Access Token ; "+employeeAccessToken);

        response = EmployeeFunctions.checkEmployeePassword(properties, employeeData.email, employeeData.incorrectPassword, employeeAccessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"invalid password");
        softAssert.assertAll();
    }

}
