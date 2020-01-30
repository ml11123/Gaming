package com.gaming.baby.payload.response;

import lombok.Data;

@Data
public class SignInResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private String uid;

    public SignInResponse(String accessToken, String uid){
        this.accessToken = accessToken;
        this.uid = uid;
    }
}
