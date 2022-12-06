package com.example.daonv_onemoung.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AuditRecord {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditRecord.class);
    private static final String Success = "success";
    private static final String Attempt = "attempt";
    private static final String Fail = "fail";
    public String apiPath;
    public String event;
    public String status;
    public String userId;
    public String userEmail;
    public String sessionId;
    public String client;
    public String ipAddress;
    public Map<String, Object> meta;

    public void success() {
        this.status = Success;
    }

    public void attempt() {
        this.status = Attempt;
    }

    public void fail() {
        this.status = Fail;
    }

    public void addMeta(String name, Object val) {
        if (meta == null) {
            meta = new HashMap<>();
        }
        try {
            if (val.getClass().equals(String.class)) {
                meta.put(name, (String) val);
                return;
            }
            meta.put(name, val);
        } catch (RuntimeException ex) {
            LOGGER.error(ex.getMessage());
        }
    }



    public static String getSuccess() {
        return Success;
    }

    public static String getAttempt() {
        return Attempt;
    }

    public static String getFail() {
        return Fail;
    }

    @Override
    public String toString() {
        String metaConv = "";
        if (meta != null) {
            metaConv = meta.toString();
        }
        metaConv = "{ " + metaConv + " }";
        return "api_path='" + apiPath + '\'' +
                ", event='" + event + '\'' +
                ", status='" + status + '\'' +
                ", user_id='" + userId + '\'' +
                ", session_id='" + sessionId + '\'' +
                ", client='" + client + '\'' +
                ", ip_address='" + ipAddress + '\'' +
                ", meta=" + metaConv;

    }
}
