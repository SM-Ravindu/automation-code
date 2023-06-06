package tests.com.nimi.api.tests;

import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.functions.AdminFunctions;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.nimi.api.utility.NumberUtil;

public class CheckEmailRegisteredAsAdminTests extends APITestBase{
    Response response;
    SoftAssert softAssert = new SoftAssert();
    NumberUtil numberUtil = new NumberUtil();
    String newEmail = "automationtester"+numberUtil.generateRandomNo()+"@gmail.com";

    @Test(priority = 1)
    public void checkEmailRegisteredAsAdminWithNewEmail(){
        response = AdminFunctions.checkEmailRegisterAsAdmin(properties, newEmail, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.DATA),"email not registered");
        softAssert.assertAll();
    }

}
