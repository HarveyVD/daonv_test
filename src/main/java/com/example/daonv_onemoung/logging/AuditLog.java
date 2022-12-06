package com.example.daonv_onemoung.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

public class AuditLog {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLog.class);

    public static AuditRecord makeAuditRecord(String event, String initialStatus) {
        AuditRecord rec = new AuditRecord();
        rec.apiPath = MDC.get("api_path");
        rec.client = MDC.get("user_agent");
        rec.event = event;
        rec.status = initialStatus;
        rec.ipAddress = MDC.get("request_ip");
        rec.sessionId = null;
        rec.userId = MDC.get("user_id");
        rec.userEmail = MDC.get("user_email");
        rec.meta = null;
        return rec;
    }

    public AuditModel logAuditApiWithUser(String userId, String event, String extraInfo) {
        AuditModel model = new AuditModel();
        model.createdDate = Instant.now().toEpochMilli();
        model.action = MDC.get("api_path");
        model.extraInfo = extraInfo;
        model.ipAddress = MDC.get("request_ip");
        model.userId = userId;
        model.type = AuditModel.API_TYPE;
        model.event = event;
        return model;
    }

    public static void logAuditRecToLogger(AuditRecord rec) {
        if (rec.status.equals(AuditRecord.getSuccess()) || rec.status.equals(AuditRecord.getAttempt())) {
            LOGGER.info(rec.toString());
        }
        if (rec.status.equals(AuditRecord.getFail())) {
            LOGGER.error(rec.toString());
        }
    }

}
