package com.example.daonv_onemoung.controlleradvice;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;

@Slf4j
@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRequestBodyAdviceAdapter.class);

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // get information of user
        try {
            String bodyRaw = body.toString();
            MDC.put("request_body", bodyRaw);
            MDC.put("api_path", httpServletRequest.getRequestURI());

            String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = httpServletRequest.getRemoteAddr();
                MDC.put("request_ip", ipAddress);
            }
            String userAgent = httpServletRequest.getHeader("user-agent");
            if (userAgent != null) {
                MDC.put("user_agent", userAgent);
            }

        } catch (RuntimeException ex) {
            LOGGER.error(ex.getMessage());
        }


        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
