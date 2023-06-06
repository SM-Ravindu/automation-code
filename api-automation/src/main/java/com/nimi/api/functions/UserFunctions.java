package com.nimi.api.functions;

import com.nimi.RequestUtil;
import com.nimi.api.common.CommonConstants;
import com.nimi.api.common.Endpoints;
import com.nimi.api.common.JsonFilePaths;
import com.nimi.api.requests.nimikash.user.InviteUser;
import com.nimi.api.utility.JsonReaderUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UserFunctions {
    public static RequestUtil requestUtil;
    static Response response;
    static InviteUser inviteAUserObj = new InviteUser();

    public static Response inviteUser(Properties prop, String email, int type, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        inviteAUserObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_INVITE_USER, InviteUser.class);
        inviteAUserObj.setEmail(email);
        inviteAUserObj.setType(type);
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");
        return requestUtil.post(Endpoints.USER.endpointUrl() + Endpoints.INVITE.endpointUrl(), inviteAUserObj, headers);
    }
}
