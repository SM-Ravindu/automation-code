package com.nimi.api.requests.nimikash.user;
import lombok.Data;

@Data
public class InviteUser {
    private String email;
    private int type;
}
