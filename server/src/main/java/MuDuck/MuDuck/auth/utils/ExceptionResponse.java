package MuDuck.MuDuck.auth.utils;

import io.jsonwebtoken.JwtException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ExceptionResponse {

    private final Map<String, Object> body = new HashMap<>();

    public Map<String, Object> createUnauthorizedErrorResponse(HttpServletRequest request, HttpServletResponse response,
            JwtException exception){
        log.error("JwtException : {}", exception.getMessage());
        // 응답 객체 초기화
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", exception.getMessage());
        body.put("path", request.getServletPath());

        return body;
    }

    public Map<String, Object> createUnauthorizedErrorResponse(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException exception){
        log.error("AccessDeniedException : {}", exception.getMessage());
        // 응답 객체 초기화
        body.put("status", HttpServletResponse.SC_FORBIDDEN);
        body.put("error", "Forbidden");
        body.put("message", exception.getMessage());
        body.put("path", request.getServletPath());

        return body;
    }

    public Map<String, Object> createUnauthorizedErrorResponse(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception){
        log.error("AuthenticationException : {}", exception.getMessage());
        // 응답 객체 초기화
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", "Token required");
        body.put("path", request.getServletPath());

        return body;
    }

}
