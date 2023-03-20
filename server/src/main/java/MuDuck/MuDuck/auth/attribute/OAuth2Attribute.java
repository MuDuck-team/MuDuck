package MuDuck.MuDuck.auth.attribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@Slf4j
@Getter
public class OAuth2Attribute {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String username;
    private String nickname;
    private String useremail;
    private String picture;

    @Builder
    public OAuth2Attribute(Map<String, Object> attributes, String nameAttributeKey, String username,
            String nickname, String useremail, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.username = username;
        this.nickname = nickname;
        this.useremail = useremail;
        this.picture = picture;
    }

    // 해당 OAuth2 로그인의 도메인 따른 분기설정
    public static OAuth2Attribute of(String registrationId, String userNameAttributeName,
            Map<String, Object> attributes) throws JsonProcessingException {

        log.info("userNameAttributeName = {}", new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(userNameAttributeName));
        log.info("attributes = {}",
                new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(attributes));

        String lowerCaseId = registrationId.toLowerCase();

        if ("naver".equals(lowerCaseId)) {
            log.info("naver OAuth2 Login");
            return ofNaver("id", attributes);
        } else if ("github".equals(lowerCaseId)) {
            log.info("github OAuth2 Login");
            return ofGithub(userNameAttributeName, attributes);
        } else if ("kakao".equals(lowerCaseId)) {
            log.info("kakao OAuth2 Login");
            return ofKakao(userNameAttributeName, attributes);
        }
        log.info("google OAuth2 Login");
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2Attribute ofGithub(String userNameAttributeName,
            Map<String, Object> attributes) {
        String nickname = ObjectUtils.isEmpty(attributes.get("name")) ? "login" : "name";
        return OAuth2Attribute.builder()
                .nickname((String) attributes.get(nickname))
                .useremail((String) attributes.get("email"))
                .picture((String) attributes.get("avatar_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attribute ofGoogle(String userNameAttributeName,
            Map<String, Object> attributes) {
        return OAuth2Attribute.builder()
                .username(attributes.get("name").toString())
                .useremail(attributes.get("email").toString())
                .picture(attributes.get("picture").toString())
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuth2Attribute ofNaver(String userNameAtrributeName,
            Map<String, Object> attributes) throws JsonProcessingException {

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        log.info("response = {}",
                new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(response));

        return OAuth2Attribute.builder()
                .username(response.get("name").toString())
                .useremail(response.get("email").toString())
                .picture(response.get("profile_image").toString())
                .nickname(response.get("nickname").toString())
                .attributes(response)
                .nameAttributeKey(userNameAtrributeName)
                .build();
    }

    private static OAuth2Attribute ofKakao(String userNameAttributeName,
            Map<String, Object> attributes) {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");
        return OAuth2Attribute.builder()
                .username((String) profile.get("nickname"))
                .useremail((String) account.get("email"))
                .picture((String) profile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
}
