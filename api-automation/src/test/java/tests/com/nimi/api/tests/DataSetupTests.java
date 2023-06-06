package tests.com.nimi.api.tests;

import com.nimi.api.common.CommonConstants;
import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.data.MailTmData;
import com.nimi.api.data.OtpData;
import com.nimi.api.functions.AdminFunctions;
import com.nimi.api.functions.MailTmFunctions;
import com.nimi.api.functions.OtpFunctions;
import com.nimi.api.utility.DateUtil;
import com.nimi.api.utility.ResponseUtil;
import com.nimi.api.utility.StringUtil;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.awaitility.Awaitility;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSetupTests extends APITestBase {
    MailTmData mailTmData = new MailTmData();
    Date date = new Date();
    String domain = null;
    Response response;
    SoftAssert softAssert = new SoftAssert();
    private String dateTimeFormat = "ddMMyyyymmss";
    private String mailTmToken;
    private String extractOtpRegex = "[\\d0-9]{6}";
    private String otp;
    public static String newEmail;

    @BeforeSuite()
    public void setUp(){
        properties = getEnvData(CommonConstants.ENV);
        domain = MailTmFunctions.retrieveMailTmDomain(properties);
        newEmail = DateUtil.getDateAndTime(date,dateTimeFormat) + mailTmData.emailId + domain;
    }

    @Test(priority = 1)
    public void registerNewAdmin(){

        response = MailTmFunctions.createMailTmAccount(properties, newEmail);

        logger.info("MailTM Create Account Response: " + response.body().prettyPrint());

        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);

        mailTmToken = MailTmFunctions.retrieveMailTmToken(properties, newEmail);
        logger.info("Token: " + mailTmToken);

        accessToken = AdminFunctions.registerAdmin(properties, newEmail);
        logger.info("Access Token ; "+accessToken);

        response = OtpFunctions.sendOtp(properties, newEmail, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.MESSAGE), OtpData.otpSendMsg);
        logger.info("OTP Send Response: " + response.body().prettyPrint());

        Awaitility.await().atMost(15, TimeUnit.SECONDS).until(() -> ResponseUtil.extractJsonPathStringValue(MailTmFunctions.retrieveOtpFromMailTm(properties, mailTmToken), JsonPathConstants.HYDRA_MEMBER_INTRO) != null);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        logger.info("Mail TM Messages Response: " + response.body().prettyPrint());
        String message = ResponseUtil.extractJsonPathStringValue(MailTmFunctions.retrieveOtpFromMailTm(properties, mailTmToken), JsonPathConstants.HYDRA_MEMBER_INTRO);
        otp = StringUtil.retrieveSubStringFromRegex(message, extractOtpRegex);

        response = OtpFunctions.verifyOtp(properties, newEmail, otp, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertEquals(response.body().jsonPath().get(JsonPathConstants.MESSAGE), OtpData.otpVerifyMsg);
        logger.info("OTP Verify Response: " + response.body().prettyPrint());
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void adminLogin(){
        response = AdminFunctions.adminLogin(properties, newEmail);

        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        adminAccessToken = ResponseUtil.extractJsonHeaderValue(response, JsonPathConstants.ACCESS_TOKEN);
        logger.info("Access Token ; "+adminAccessToken);
        System.out.println("Access Token ; "+adminAccessToken);
        softAssert.assertAll();
    }
}
