package MuDuck.MuDuck.auth.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class ExceptionResponse {

    public Map<String, Object> createUnauthorizedErrorResponse(HttpServletRequest request, HttpServletResponse response,
            Exception exception){

        Map<String, Object> body = new HashMap<>();
        // 응답 객체 초기화
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", exception.getMessage());
        body.put("path", request.getServletPath());

        return body;
    }

}
