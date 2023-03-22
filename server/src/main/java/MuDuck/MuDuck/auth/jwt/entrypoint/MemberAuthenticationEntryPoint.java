package MuDuck.MuDuck.auth.jwt.entrypoint;

import MuDuck.MuDuck.auth.utils.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// 인증에 실패했을 때 호출
@Component
@RequiredArgsConstructor
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ExceptionResponse exceptionResponse;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        Map<String, Object> body = exceptionResponse.createUnauthorizedErrorResponse(request, response, authException);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final ObjectMapper mapper = new ObjectMapper();
        // response 객체에 응답 객체를 넣어줌
        //mapper.writeValue(response.getOutputStream(), body);
        String content = mapper.writeValueAsString(body);
        response.getWriter().write(content);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
