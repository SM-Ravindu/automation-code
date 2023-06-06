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

public class RegisterEmployeeTests extends APITestBase{
    Response response;
    SoftAssert softAssert = new SoftAssert();
    InviteEmployeeData invteEmployeeData = new InviteEmployeeData();
    EmployeeData employeeData = new EmployeeData();
    String employeeAccessToken = null;
    NumberUtil numberUtil = new NumberUtil();
    String newEmail = "automationtester"+numberUtil.generateRandomNo()+"@gmail.com";
    String newEmployeeId = "EMP-"+numberUtil.generateRandomNo();

    @Test(priority = 1)
    public void registerNewEmployeeWithAllValidData(ITestContext context){
        List<Map<String, Object>> bulkFields = new ArrayList<>();
        Map<String, Object> employeeFields = new HashMap<>();
        employeeFields.put("employeeId", newEmployeeId);
        employeeFields.put("email", newEmail);
        employeeFields.put("currency", invteEmployeeData.currency);
        employeeFields.put("designation", invteEmployeeData.designation);
        employeeFields.put("salary", invteEmployeeData.salary);
        bulkFields.add(employeeFields);

        response = EmployeeFunctions.registerEmployee(properties, bulkFields, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        ISuite suite = context.getSuite();
        suite.setAttribute("EmployeeEmail", newEmail);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void employeeLogin(){
        employeeAccessToken = EmployeeFunctions.employeeLogin(properties, employeeData.email, employeeData.password);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        employeeAccessToken = ResponseUtil.extractJsonHeaderValue(response, JsonPathConstants.ACCESS_TOKEN);
        logger.info("Access Token : "+employeeAccessToken);
        System.out.println("Access Token ; "+employeeAccessToken);
        softAssert.assertAll();
    }

}
