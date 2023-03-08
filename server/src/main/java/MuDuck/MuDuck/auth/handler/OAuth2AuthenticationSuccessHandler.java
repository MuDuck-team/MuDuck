package MuDuck.MuDuck.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final Gson gson;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("성공!");

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        log.info("token.getName() : {} ", token.getName());

        OAuth2User principal = token.getPrincipal();

        Map<String, Object> attributes = principal.getAttributes();

        String authorizedClientRegistrationId = token.getAuthorizedClientRegistrationId();

        log.info("로그인 플랫폼 : {}", authorizedClientRegistrationId);

        log.info("안에 있는 정보들 : {}", attributes);

        String email = (String) attributes.get("email");
        String imageUrl = "";
        // 로그인 플랫폼에 따른 분기
        if(authorizedClientRegistrationId.equals("github")){

        }else if(authorizedClientRegistrationId.equals("naver")){

            imageUrl = (String) attributes.get("profile_image");

        }else if(authorizedClientRegistrationId.equals("google")){

            imageUrl = (String) attributes.get("picture");

        }


        // 추가정보를 위해서
        String uri = UriComponentsBuilder.fromUriString("http://localhost:8080/test/user")
                    .build().toUriString();

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        String responseEmail = objectMapper.writeValueAsString(email);

        response.getWriter().write(responseEmail);

        response.sendRedirect(uri);


    }

}
