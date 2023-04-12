package com.work.ymy.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.work.ymy.helper.Constant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class BasicAuthEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        Map<ECommence, Object> map = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        map.put(ECommence.status,HttpServletResponse.SC_UNAUTHORIZED);
        map.put(ECommence.error, Constant.ERROR_UNAUTHORIZED);
        map.put(ECommence.message, authenticationException.getMessage());
        map.put(ECommence.path, request.getServletPath());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate","Basic realm=" + getRealmName() + "");
        mapper.writeValue(response.getOutputStream(),map);
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("basicAuthApp");
        super.afterPropertiesSet();
    }

}
