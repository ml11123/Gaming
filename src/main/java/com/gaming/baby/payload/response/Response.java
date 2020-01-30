package com.gaming.baby.payload.response;

public class Response {

    boolean status = true;
    String message;
    Object model;

    public Response() {

    }

    public Response(boolean status, String message, Object model) {
        this.status = status;
        this.message = message;
        this.model = model;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {
        this.model = model;
    }
}
