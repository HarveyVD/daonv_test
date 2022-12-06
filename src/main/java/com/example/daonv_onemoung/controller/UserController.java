package com.example.daonv_onemoung.controller;

import com.example.daonv_onemoung.common.SimpleResponseData;
import com.example.daonv_onemoung.common.UserDTO;
import com.example.daonv_onemoung.logging.AuditLog;
import com.example.daonv_onemoung.logging.AuditRecord;
import com.example.daonv_onemoung.service.UserServiceImpl;
import com.example.daonv_onemoung.service.facade.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getBookingInfo(@PathVariable("id") Long id) {
        SimpleResponseData responseData = new SimpleResponseData();
        //
        try {
            UserDTO returnData = userService.getUser(id);
            if (returnData == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(returnData);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.notFound().build();
        }
        //
    }

    @PostMapping(value = "/create", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO req) {
        AuditRecord auditRecord = AuditLog.makeAuditRecord("create-user", AuditRecord.getFail());
        try {
            UserDTO dto = userService.saveUser(req);
            auditRecord.addMeta("result", req);
            auditRecord.success();
            AuditLog.logAuditRecToLogger(auditRecord);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity.badRequest().build();
        }
    }
}
