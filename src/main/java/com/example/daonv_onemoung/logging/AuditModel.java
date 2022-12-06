package com.example.daonv_onemoung.logging;

public class AuditModel {
    public static final String API_TYPE = "audit_api";

    public Long createdDate;
    public String userId;
    public String action;
    public String extraInfo;
    public String ipAddress;
    public String type;
    public String event;
}
