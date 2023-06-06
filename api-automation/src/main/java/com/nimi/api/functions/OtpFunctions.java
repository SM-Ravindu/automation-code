package com.nimi.api.functions;

import com.nimi.RequestUtil;
import com.nimi.api.common.CommonConstants;
import com.nimi.api.common.Endpoints;
import com.nimi.api.common.JsonFilePaths;
import com.nimi.api.requests.nimikash.admin.OtpSend;
import com.nimi.api.requests.nimikash.admin.OtpVerify;
import com.nimi.api.utility.JsonReaderUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OtpFunctions {

    public static RequestUtil requestUtil;
    static OtpSend otpSendObj = new OtpSend();
    static OtpVerify otpVerify = new OtpVerify();

    public static Response sendOtp(Properties prop, String email, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        otpSendObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_OTP_SEND, OtpSend.class);
        otpSendObj.setEmail(email);
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");
        return requestUtil.post(Endpoints.OTP.endpointUrl() + Endpoints.SEND.endpointUrl(), otpSendObj, headers);
    }

    public static Response verifyOtp(Properties prop, String email, String otp, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        otpVerify = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_OTP_VERIFY, OtpVerify.class);
        otpVerify.setEmail(email);
        otpVerify.setOtp(otp);
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");
        return requestUtil.post(Endpoints.OTP.endpointUrl() + Endpoints.VERIFY.endpointUrl(), otpVerify, headers);
    }
}
