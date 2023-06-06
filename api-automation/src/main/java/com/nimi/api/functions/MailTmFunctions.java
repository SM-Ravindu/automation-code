package com.nimi.api.functions;

import com.nimi.RequestUtil;
import com.nimi.api.common.CommonConstants;
import com.nimi.api.common.Endpoints;
import com.nimi.api.common.JsonFilePaths;
import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.requests.mailtm.CreateAccount;
import com.nimi.api.utility.JsonReaderUtil;
import com.nimi.api.utility.ResponseUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MailTmFunctions {

    public static RequestUtil requestUtil;
    static Response response;
    static CreateAccount createAccountObj = new CreateAccount();

    public static String retrieveMailTmDomain(Properties prop){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.MAIL_TM_URL), "");
        response = requestUtil.get(Endpoints.DOMAINS.endpointUrl());
        return ResponseUtil.extractJsonPathStringValue(response, JsonPathConstants.HYDRA_MEMBER_DOMAIN);
    }

    public static Response createMailTmAccount(Properties prop, String email){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.MAIL_TM_URL), "");
        createAccountObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_MAIL_TM_CREATE_ACC, CreateAccount.class);
        createAccountObj.setAddress(email);
        return requestUtil.post(Endpoints.ACCOUNTS.endpointUrl(), createAccountObj);
    }

    public static String retrieveMailTmToken(Properties prop, String email){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.MAIL_TM_URL), "");
        createAccountObj.setAddress(email);
        response = requestUtil.post(Endpoints.TOKEN.endpointUrl(), createAccountObj);
        return ResponseUtil.extractJsonPathStringValue(response, JsonPathConstants.MAIL_TM_TOKEN);
    }

    public static Response retrieveOtpFromMailTm(Properties prop, String mailTmToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.MAIL_TM_URL), "");
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + mailTmToken);

        return requestUtil.get(Endpoints.MESSAGES.endpointUrl(), headers);
    }
}
