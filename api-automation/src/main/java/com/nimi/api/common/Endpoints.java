package com.nimi.api.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum Endpoints {
    DOMAINS("/domains"),
    ACCOUNTS("/accounts"),
    ADMIN("/admin"),
    REGISTER("/register"),
    OTP("/otp"),
    SEND("/send"),
    TOKEN("/token"),
    MESSAGES("/messages"),
    VERIFY("/verify"),
    LOGIN("/login"),
    COMPANY("/company"),
    INVITE("/invite"),
    GET("/get"),
    USER("/user"),
    EMPLOYEE("/employee"),
    PASSWORD("/password"),
    CHECK("/check"),
    ADD("/add");

    private String endpoint;

    private Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    public String endpointUrl() {
        return endpoint;
    }

    public PathParameterBuilder pathParameters() {
        return new PathParameterBuilder(this);
    }

    public static class PathParameterBuilder {
        private List<String> pathParamList;
        private Endpoints endpointType;

        private PathParameterBuilder(Endpoints endpointType) {
            this.pathParamList = new ArrayList<>();
            this.endpointType = endpointType;
        }

        public PathParameterBuilder withParameterMap(Map<String,Object> paramsMap) {
            paramsMap.forEach((k,v) -> {
                pathParamList.add(v.toString());
            });
            return this;
        }

        public PathParameterBuilder addParameter(String param) {
            pathParamList.add(param);
            return this;
        }

        public String endpointWithPathParams() {
            String endpointUrl = endpointType.endpointUrl();
            for (String param : pathParamList) {
                endpointUrl += "/" + param;
            }
            return endpointUrl;
        }
    }
}
