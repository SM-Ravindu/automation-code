package com.nimi.api.functions;

import com.nimi.RequestUtil;
import com.nimi.api.common.CommonConstants;
import com.nimi.api.common.Endpoints;
import com.nimi.api.common.JsonFilePaths;
import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.requests.nimikash.admin.AdminLogin;
import com.nimi.api.requests.nimikash.admin.AdminRegister;
import com.nimi.api.requests.nimikash.admin.InviteAdmin;
import com.nimi.api.utility.JsonReaderUtil;
import com.nimi.api.utility.ResponseUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AdminFunctions {

    public static RequestUtil requestUtil;
    static Response response;
    static AdminRegister adminRegisterObj = new AdminRegister();
    static AdminLogin adminLoginObj = new AdminLogin();
    static InviteAdmin inviteAdminObj = new InviteAdmin();

    public static String registerAdmin(Properties prop, String email){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        adminRegisterObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_ADMIN_REGISTER, AdminRegister.class);
        adminRegisterObj.setEmail(email);
        response = requestUtil.post(Endpoints.ADMIN.endpointUrl() + Endpoints.REGISTER.endpointUrl(), adminRegisterObj);

        return ResponseUtil.extractJsonHeaderValue(response, JsonPathConstants.ACCESS_TOKEN);
    }

    public static Response adminLogin(Properties prop, String email){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        adminLoginObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_ADMIN_LOGIN, AdminLogin.class);
        adminLoginObj.setEmail(email);

        return requestUtil.post(Endpoints.ADMIN.endpointUrl() + Endpoints.LOGIN.endpointUrl(), adminLoginObj);
    }

    public static Response inviteAdmin(Properties prop, String name, String email, int type, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        inviteAdminObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_INVITE_ADMIN, InviteAdmin.class);
        inviteAdminObj.setName(name);
        inviteAdminObj.setEmail(email);
        inviteAdminObj.setType(type);
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");

        return requestUtil.post(Endpoints.ADMIN.endpointUrl() + Endpoints.INVITE.endpointUrl(), inviteAdminObj, headers);
    }

    public static Response checkEmailRegisterAsAdmin(Properties prop, String email, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");

        return requestUtil.post(Endpoints.ADMIN.endpointUrl() + Endpoints.GET.endpointUrl()+"/"+email , headers);
    }
}
