package tests.com.nimi.api.tests;

import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.functions.AdminFunctions;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.nimi.api.data.InviteAdminData;
import com.nimi.api.utility.NumberUtil;

public class InviteAdminTests extends APITestBase {
    Response response;
    SoftAssert softAssert = new SoftAssert();
    InviteAdminData inviteAdminData = new InviteAdminData();
    NumberUtil numberUtil = new NumberUtil();
    String newEmail = "automationtester"+numberUtil.generateRandomNo()+"@gmail.com";


    @Test(priority = 1)
    public void inviteNewAdminWithAlreadyExistingEmail(){
        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.name, inviteAdminData.alreadyExistingEmail, inviteAdminData.adminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"email exists");
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void inviteNewAdminWithNameInInvalidFormat(){
        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.nameInInvalidFormat, inviteAdminData.alreadyExistingEmail, inviteAdminData.adminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"invalid name format");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void inviteNewAdminWithEmailInInvalidFormat(){
        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.name, inviteAdminData.emailInInvalidFormat, inviteAdminData.adminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"invalid email format");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void inviteNewAdminWithWrongAdminType(){
        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.name, newEmail, inviteAdminData.wrongAdminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"invalid admin type");
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void inviteNewAdminWithoutMandatoryFields(){
        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.emptyName, newEmail, inviteAdminData.adminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"name field is empty");

        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.name, inviteAdminData.emptyEmail, inviteAdminData.adminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"email field is empty");
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void inviteNewAdminWithSuperAdminDoesntHaveCompanyRegistered(){
        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.name, newEmail, inviteAdminData.adminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertFalse(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.ERROR),"admin company empty");
        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void inviteNewAdminWithAllValidData(ITestContext context){
        response = AdminFunctions.inviteAdmin(properties, inviteAdminData.name, newEmail, inviteAdminData.adminType, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.MESSAGE+".adminName"),inviteAdminData.name);
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.MESSAGE+".adminEmail"),newEmail);
        ISuite suite = context.getSuite();
        suite.setAttribute("AdminEmail", response.body().jsonPath().get(JsonPathConstants.MESSAGE+".adminEmail"));
        softAssert.assertAll();
    }

}
