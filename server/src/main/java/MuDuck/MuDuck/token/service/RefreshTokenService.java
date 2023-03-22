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

        accessToken = createAccessToken(refreshToken);

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
