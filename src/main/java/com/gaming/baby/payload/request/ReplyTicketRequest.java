package com.gaming.baby.payload.request;

public class ReplyTicketRequest {
    private long ticket_id;
    private String message;

    public ReplyTicketRequest(){

    }

    public ReplyTicketRequest(long ticket_id, String message) {
        this.ticket_id = ticket_id;
        this.message = message;
    }

    public long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
