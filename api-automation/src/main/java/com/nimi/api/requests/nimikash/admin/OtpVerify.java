package com.nimi.api.requests.nimikash.admin;

import lombok.Data;

@Data
public class OtpVerify {

    public String email;
    public String otp;
    public int mode;
    public boolean isAdmin;
}
