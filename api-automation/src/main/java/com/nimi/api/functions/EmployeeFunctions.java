package com.nimi.api.functions;

import com.nimi.RequestUtil;
import com.nimi.api.common.CommonConstants;
import com.nimi.api.common.Endpoints;
import com.nimi.api.common.JsonFilePaths;
import com.nimi.api.common.JsonPathConstants;
import com.nimi.api.requests.nimikash.employee.EmployeeLogin;
import com.nimi.api.requests.nimikash.employee.EmployeeRegister;
import com.nimi.api.utility.JsonReaderUtil;
import com.nimi.api.utility.ResponseUtil;
import io.restassured.response.Response;

import java.util.*;
public class EmployeeFunctions {
    public static RequestUtil requestUtil;
    static EmployeeRegister employeeRegisterObj = new EmployeeRegister();
    static EmployeeLogin employeeLoginObj = new EmployeeLogin();
    static Response response;
    static String employeeAccessToken = null;

    public static Response registerEmployee(Properties prop, List<Map<String, Object>> bulkFields, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));
        employeeRegisterObj.setBulkFields(bulkFields);
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");
        return requestUtil.post(Endpoints.EMPLOYEE.endpointUrl() + Endpoints.ADD.endpointUrl(), employeeRegisterObj, headers);
    }

    public static String employeeLogin(Properties prop, String email, String password){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));
        employeeLoginObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_EMPLOYEE_LOGIN, EmployeeLogin.class);
        employeeLoginObj.setEmail(email);
        employeeLoginObj.setPassword(password);
        response = requestUtil.post(Endpoints.EMPLOYEE.endpointUrl() + Endpoints.LOGIN.endpointUrl(), employeeLoginObj);
        return ResponseUtil.extractJsonHeaderValue(response, JsonPathConstants.ACCESS_TOKEN);
    }

    public static Response checkEmployeePassword(Properties prop, String email, String password, String accessToken){
        requestUtil = RequestUtil.setRequestUtils(prop.getProperty(CommonConstants.BASE_URL), prop.getProperty(CommonConstants.BASE_PATH));

        employeeLoginObj = JsonReaderUtil.readFromJsonFile(JsonFilePaths.FILE_PATH_EMPLOYEE_LOGIN, EmployeeLogin.class);
        employeeLoginObj.setEmail(email);
        employeeLoginObj.setPassword(password);
        Map<String,Object> headers = new HashMap<>();
        headers.put("Authorization", "\"" + accessToken + "\"");

        return requestUtil.post(Endpoints.EMPLOYEE.endpointUrl() + Endpoints.PASSWORD.endpointUrl() + Endpoints.CHECK.endpointUrl(), employeeLoginObj, headers);
    }
}
