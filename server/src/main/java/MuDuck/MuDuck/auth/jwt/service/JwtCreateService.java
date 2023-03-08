package MuDuck.MuDuck.auth.jwt.service;

import MuDuck.MuDuck.auth.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtCreateService {
    private final JwtTokenizer jwtTokenizer;

    // accessToken 생성
//    public String delegateAccessToken(Member member){
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", member.getEmail());
//        claims.put("roles", member.getMemberRole().getRole());
//
//        String subject = member.getEmail();
//        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());
//
//        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
//
//        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);
//
//        return accessToken;
//    }
//
//    // refreshToken 생성
//    public String delegateRefreshToken(Member member){
//        String subject = member.getEmail();
//
//        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
//
//        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
//
//        String refreshToken = jwtTokenizer.generateRefreshToken(subject, expiration, base64EncodedSecretKey);
//
//        return refreshToken;
//    }
}
