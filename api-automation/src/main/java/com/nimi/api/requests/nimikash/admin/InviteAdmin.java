package com.nimi.api.requests.nimikash.admin;
import lombok.Data;

@Data
public class InviteAdmin {
    private String name;
    private String email;
    private int type;
}
