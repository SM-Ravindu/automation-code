package tests.com.nimi.api.tests;

import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.functions.CompanyFunctions;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AdminTests extends APITestBase {

    Response response;
    SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    public void registerNewCompany(){

        response = CompanyFunctions.registerCompany(properties, accessToken);

        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        logger.info("Company Register Response: " + response.body().prettyPrint());
        softAssert.assertAll();
    }
}
