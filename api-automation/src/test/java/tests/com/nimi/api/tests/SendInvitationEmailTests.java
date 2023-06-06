package tests.com.nimi.api.tests;

import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.functions.UserFunctions;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.nimi.api.data.InviteUserData;

public class SendInvitationEmailTests extends APITestBase {
    Response response;
    SoftAssert softAssert = new SoftAssert();
    InviteUserData inviteUserData = new InviteUserData();

    @Test(priority = 1)
    public void inviteAdminWithAllValidData(ITestContext context){
        ISuite suite = context.getSuite();
        String adminEmail = (String) suite.getAttribute("AdminEmail");
        response = UserFunctions.inviteUser(properties, adminEmail, inviteUserData.adminEmailType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.MESSAGE),"admin email sent");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void inviteEmployeeWithAllValidData(ITestContext context){
        ISuite suite = context.getSuite();
        String employeeEmail = (String) suite.getAttribute("EmployeeEmail");
        response = UserFunctions.inviteUser(properties, employeeEmail, inviteUserData.employeeEmailType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.MESSAGE),"employee email sent");
        softAssert.assertAll();
    }
}
