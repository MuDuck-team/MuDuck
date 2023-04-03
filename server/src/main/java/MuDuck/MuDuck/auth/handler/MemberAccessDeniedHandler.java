package MuDuck.MuDuck.auth.handler;

import MuDuck.MuDuck.auth.utils.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

// 인증은 성공했지만 리소스에 대한 권한이 없는 경우 호출
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberAccessDeniedHandler implements AccessDeniedHandler {

    private final ExceptionResponse exceptionResponse;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //ErrorResponder.sendErrorResponse(response, HttpStatus.FORBIDDEN);
        log.warn("Forbidden error happened: {}", accessDeniedException.getMessage());
        log.info("권한이 없는 접근");

        Map<String, Object> body = exceptionResponse.createUnauthorizedErrorResponse(request, response, accessDeniedException);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        final ObjectMapper mapper = new ObjectMapper();
        // response 객체에 응답 객체를 넣어줌
        String content = mapper.writeValueAsString(body);
        response.getWriter().write(content);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
