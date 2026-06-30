package com.teamflow.teamflow.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {

    public Long getUserId(HttpServletRequest request) {
        String userId = request.getHeader("X-User-Id");
        if (userId == null) {
            throw new RuntimeException("User ID header missing");
        }
        return Long.parseLong(userId);
    }
}