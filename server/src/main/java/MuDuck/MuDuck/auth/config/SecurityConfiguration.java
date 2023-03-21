package MuDuck.MuDuck.auth.config;

import MuDuck.MuDuck.auth.handler.MemberLogoutSuccessHandler;
import MuDuck.MuDuck.auth.handler.OAuth2AuthenticationFailureHandler;
import MuDuck.MuDuck.auth.handler.OAuth2AuthenticationSuccessHandler;
import MuDuck.MuDuck.auth.jwt.JwtTokenizer;
import MuDuck.MuDuck.auth.jwt.filter.JwtAuthenticationProcessingFilter;
import MuDuck.MuDuck.auth.jwt.filter.JwtVerificationFilter;
import MuDuck.MuDuck.auth.service.CustomOAuth2UserService;
import MuDuck.MuDuck.auth.utils.CustomAuthorityUtils;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final MemberLogoutSuccessHandler memberLogoutSuccessHandler;
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils customAuthorityUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .cors(httpSecurityCorsConfigurer -> corsConfigurationSource())
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                // 접근 권한 설정
                .authorizeRequests(auth -> auth
                        .antMatchers(HttpMethod.PATCH, "/members/**", "/answers/**",
                                "/questions/**").hasRole("USER")
                        .antMatchers(HttpMethod.POST, "/answers/**", "/questions/**")
                        .hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/members/**", "/answers/**",
                                "/questions/**").hasRole("USER")
                        //.antMatchers("/h2/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/auth/login/**").permitAll()
                        .anyRequest().permitAll()
                )
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                // 성공 시 Handler
                .successHandler(oAuth2AuthenticationSuccessHandler)
                // 실패 시 Handler
                .failureHandler(oAuth2AuthenticationFailureHandler)
                .and()
                .logout().logoutSuccessHandler(memberLogoutSuccessHandler);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Location", "Refresh"));

        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Jwt 검증 필터 추가
    public class CustomFilterConfigurer extends
            AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
//          // jwt 검증 필터 추가
            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, customAuthorityUtils);

            builder
                    .addFilterAfter(jwtVerificationFilter, OAuth2LoginAuthenticationFilter.class);
        }
    }
}
