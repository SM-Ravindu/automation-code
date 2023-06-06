package com.nimi.api.functions;

import com.nimi.RequestUtil;
import com.nimi.api.common.CommonConstants;
import com.nimi.api.common.Endpoints;
import com.nimi.api.common.JsonFilePaths;
import com.nimi.api.requests.nimikash.company.CompanyRegister;
import com.nimi.api.utility.JsonReaderUtil;
import com.nimi.api.utility.NumberUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CompanyFunctions {

    private static CompanyRegister companyRegisterObj = new CompanyRegister();
    public static RequestUtil requestUtil;

    public static Response registerCompany(Properties prop, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));
        companyRegisterObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_REGISTER_COMPANY, CompanyRegister.class);
        companyRegisterObj.setName(companyRegisterObj.getName() + NumberUtil.generateRandomNo());
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");
        return requestUtil.post(Endpoints.COMPANY.endpointUrl() + Endpoints.REGISTER.endpointUrl(), companyRegisterObj, headers);
    }
}
