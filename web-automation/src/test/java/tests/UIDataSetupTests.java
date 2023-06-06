package tests;

import com.nimi.RequestUtil;
import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.data.MailTmData;
import com.nimi.api.data.OtpData;
import com.nimi.api.functions.AdminFunctions;
import com.nimi.api.functions.CompanyFunctions;
import com.nimi.api.functions.MailTmFunctions;
import com.nimi.api.functions.OtpFunctions;
import com.nimi.api.utility.DateUtil;
import com.nimi.api.utility.ResponseUtil;
import com.nimi.api.utility.StringUtil;
import com.nimi.ui.common.CommonConstants;
import com.nimi.ui.pages.Pages;
import com.nimi.ui.report.ExtentManager;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.awaitility.Awaitility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UIDataSetupTests extends UITestBase{
    MailTmData mailTmData = new MailTmData();
    MailTmFunctions mailTmFunctions = new MailTmFunctions();
    Date date = new Date();
    String domain = null;
    Response response;
    SoftAssert softAssert = new SoftAssert();
    private String dateTimeFormat = "ddMMyyyymmss";
    private String mailTmToken;
    private String extractOtpRegex = "[\\d0-9]{6}";
    private String otp;
    private String empEmail;
    public static String newEmail;
    public static String accessToken;
    public static String adminAccessToken;
    protected static Log logger = LogFactory.getLog(ExtentManager.class);


    @BeforeMethod()
    public void setUp(){
        properties = getEnvData(CommonConstants.ENV);
        domain = mailTmFunctions.retrieveMailTmDomain(properties);
        newEmail = DateUtil.getDateAndTime(date,dateTimeFormat) + mailTmData.emailId + domain;
    }

    @Test
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

        Awaitility.await().atMost(20, TimeUnit.SECONDS).until(() -> ResponseUtil.extractJsonPathStringValue(MailTmFunctions.retrieveOtpFromMailTm(properties, mailTmToken), JsonPathConstants.HYDRA_MEMBER_INTRO) != null);
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

    @Test
    public void adminLogin(){
        response = AdminFunctions.adminLogin(properties, newEmail);

        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        adminAccessToken = ResponseUtil.extractJsonHeaderValue(response, JsonPathConstants.ACCESS_TOKEN);
        logger.info("Access Token ; "+adminAccessToken);
        System.out.println("Access Token ; "+adminAccessToken);
        softAssert.assertAll();
    }

    @Test
    public void registerNewCompany(){

        response = CompanyFunctions.registerCompany(properties, accessToken);
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertTrue(response.body().jsonPath().get(JsonPathConstants.SUCCESS));
        logger.info("Company Register Response: " + response.body().prettyPrint());
        softAssert.assertAll();
    }

    @Test
    public void createEmpEmail(){
        properties = getEnvData(CommonConstants.ENV);
        RequestUtil requestUtil = RequestUtil.setRequestUtils(properties.getProperty(CommonConstants.BASE_URL), properties.getProperty(CommonConstants.BASE_PATH), adminAccessToken);
        domain = MailTmFunctions.retrieveMailTmDomain(properties);
        empEmail = DateUtil.getDateAndTime(date,dateTimeFormat) + mailTmData.emailId + domain;
    }
}
