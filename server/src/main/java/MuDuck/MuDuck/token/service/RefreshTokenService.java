package MuDuck.MuDuck.token.service;

import MuDuck.MuDuck.auth.jwt.JwtTokenizer;
import MuDuck.MuDuck.auth.jwt.service.JwtCreateService;
import MuDuck.MuDuck.member.entity.Member;
import MuDuck.MuDuck.member.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {
    private final JwtTokenizer jwtTokenizer;
    private final MemberRepository memberRepository;
    private final JwtCreateService jwtCreateService;
    private final String REDIRECT_URL = "http://localhost:8080/rere";
    public String reissuanceRefreshToken(String refreshToken) {

        String accessToken = "";

        // 토큰 값 해석하기
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(
                jwtTokenizer.getSecretKey());
        try {
            Map<String, Object> claims = jwtTokenizer.getClaims(refreshToken, base64EncodedSecretKey).getBody();
        }catch (ExpiredJwtException ex){
            // 유효기간이 만료인 경우
            log.error("ExpiredJwtException : {} ", ex.getMessage());
            accessToken = "Failed";
            return accessToken;
        }catch (SignatureException | MalformedJwtException ex){
            // 토큰이 변조되거나 값이 너무 짧은 경우
            log.error("{} : {}", ex.getClass().getName(),ex.getMessage());
            accessToken = "Failed";
            return accessToken;
        }catch (Exception e){
            log.error("Exception : {}", e.getMessage());
            accessToken = "Failed";
            return accessToken;
        }

//        log.info("넘어온 토큰 값 : {} ", refreshToken);
//
//        log.info("claims 유효기간 확인 : {}", claims.get("exp"));
//        // 토큰 유효기간 가져오기
//        long exp = Long.parseLong(String.valueOf(claims.get("exp")));
//
//        log.info("유효기간 확인 : {}", exp);
//
//        Date date = new Date(exp * 1000L);
//
        accessToken = createAccessToken(refreshToken);

        // 현재 시간 가져오기
//        Date current = new Date(System.currentTimeMillis());
//
//        // 해당 시간 과 지금 시간을 비교하기
//        if(current.getTime() > date.getTime()){
//            // 유효 기간이 남아 있을 경우 재발급
//            // 해당 리프레쉬 토큰이 있는 멤버가 있는지 검증
//            accessToken = createAccessToken(refreshToken);
//        }else{
//            // 유효 기간이 지났을 경우 처리
//            accessToken = "Failed";
//        }

        return accessToken;
    }

    private String createAccessToken(String refreshToken){
        String accessToken = "";
        Optional<Member> byRefreshToken = memberRepository.findByRefreshToken(refreshToken);
        if(byRefreshToken.isPresent()){
            Member member = byRefreshToken.get();
            accessToken = "Bearer "+jwtCreateService.delegateAccessToken(member);
            log.info("재발급한 액세스 토큰 : {} ", accessToken);
        }else{
            accessToken = "Failed";
        }
        return accessToken;
    }
}
