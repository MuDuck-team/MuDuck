package MuDuck.MuDuck.auth.handler;

import MuDuck.MuDuck.auth.attribute.OAuth2Attribute;
import MuDuck.MuDuck.auth.jwt.service.JwtCreateService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtCreateService jwtCreateService;
    private final MemberRepository memberRepository;

    private final String REDIRECT_URL = "http://localhost:3000/oauth/redirect";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("성공!");

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;

        log.info("token.getName() : {} ", token.getName());

        OAuth2User principal = token.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        String authorizedClientRegistrationId = token.getAuthorizedClientRegistrationId();
        OAuth2Attribute oAuth2Attribute = OAuth2Attribute.of(authorizedClientRegistrationId, "",
                attributes);

        log.info("로그인 플랫폼 : {}", authorizedClientRegistrationId);

        log.info("안에 있는 정보들 : {}", attributes);

        String email = (String) attributes.get("email");

        boolean firstStatus = checkFirstLogin(email);

        Member member = saveOrUpdate(oAuth2Attribute);

        String accessToken = jwtCreateService.delegateAccessToken(member);
        String refreshToken = jwtCreateService.delegateRefreshToken(member);

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .maxAge(7 * 24 * 60 * 60)
                .path("/")
                .secure(true)
                .sameSite("None")
                .httpOnly(true)
                .build();

        // Header 설정
        //response.setHeader("accessToken", "Bearer " + accessToken);
        response.addHeader("Set-Cookie", cookie.toString());

        // queryParam 에 담을 MultiValue 설정
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("signup", String.valueOf(firstStatus));
        multiValueMap.add("accessToken", "Bearer "+accessToken);
        log.info("accessToken : {}", "Bearer " + accessToken);

        String uri = UriComponentsBuilder.fromUriString(REDIRECT_URL)
                .queryParams(multiValueMap)
                .build().toUriString();

        response.sendRedirect(uri);
    }

    private Member saveOrUpdate(OAuth2Attribute attributes) {
        Optional<Member> member = memberRepository.findByEmail(attributes.getUseremail());
        if (member.isPresent()) { // Member 테이블에 이미 이메일이 있다면
            return member.get();
        } else { // Member 테이블에 이메일이 없다면
            Member newMember = Member.builder()
                    .email(attributes.getUseremail())
                    .nickName(attributes.getUsername())
                    .memberRole(Member.MemberRole.USER)
                    .memberStatus(Member.MemberStatus.MEMBER_ACTIVE)
                    .picture(attributes.getPicture())
                    .build();
            return memberRepository.save(newMember);
        }
    }

    private boolean checkFirstLogin(String email){

        Optional<Member> member = memberRepository.findByEmail(email);

        return member.isEmpty();
    }

}
