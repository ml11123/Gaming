package com.gaming.baby.payload.response;

public class DashboardInfo {
    private long ticketCount;
    private long userCount;
    private long unReplyTicketCount;
    private long saleAmount;
    private long transactionAmount;

    public DashboardInfo(){}

    public DashboardInfo(long userCount, long ticketCount, long unReplyTicketCount, long saleAmount, long transactionAmount) {
        this.ticketCount = ticketCount;
        this.userCount = userCount;
        this.unReplyTicketCount = unReplyTicketCount;
        this.saleAmount = saleAmount;
        this.transactionAmount = transactionAmount;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public long getUnReplyTicketCount() {
        return unReplyTicketCount;
    }

    public void setUnReplyTicketCount(long unReplyTicketCount) {
        this.unReplyTicketCount = unReplyTicketCount;
    }

    public long getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(long saleAmount) {
        this.saleAmount = saleAmount;
    }

    public long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public long getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(long ticketCount) {
        this.ticketCount = ticketCount;
    }
}
