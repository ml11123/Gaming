package com.gaming.baby.payload.response;

public class SingleResponse extends Response {

    public SingleResponse() {
        super();
    }

    public SingleResponse(boolean status, String message, Object model) {
        super(status, message, model);
    }
}
