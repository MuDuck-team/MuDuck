package MuDuck.MuDuck.token.controller;

import MuDuck.MuDuck.token.dto.AccessTokenDto;
import MuDuck.MuDuck.token.service.RefreshTokenService;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refresh-token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    @Value("${aws.address.s3}")
    private String s3Address;
    @Value("${aws.address.login}")
    private String LOGIN_URL;
    @GetMapping("/reissuance")
    public ResponseEntity reissuanceRefreshToken(@CookieValue String refreshToken)
            throws IOException, URISyntaxException {

        String newToken = refreshTokenService.reissuanceRefreshToken(refreshToken);

        URI redirectUri = new URI(s3Address+LOGIN_URL);

        HttpHeaders httpHeaders = new HttpHeaders();

        if(newToken.equals("Failed")){
            httpHeaders.setLocation(redirectUri);
            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
        }else{
            AccessTokenDto.Response response = AccessTokenDto.Response.builder()
                    .token(newToken)
                    .status(HttpStatus.OK.value())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }
}
