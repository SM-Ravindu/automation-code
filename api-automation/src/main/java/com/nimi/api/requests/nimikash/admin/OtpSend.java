package com.nimi.api.requests.nimikash.admin;

import lombok.Data;

@Data
public class OtpSend {

    private String email;
    private String mobile;
    private String country;
    private String mode;
}
