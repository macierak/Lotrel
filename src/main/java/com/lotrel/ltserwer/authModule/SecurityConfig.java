package com.lotrel.ltserwer.authModule;

import com.lotrel.ltserwer.authModule.infrastructure.oauth2.RestAuthenticationEntryPoint;
import com.lotrel.ltserwer.authModule.infrastructure.oauth2.TokenAuthenticationFilter;
import com.lotrel.ltserwer.authModule.infrastructure.oauth2.service.CustomOAuth2UserService;
import com.lotrel.ltserwer.authModule.infrastructure.oauth2.service.HttpCookieOAuth2AuthorizationRequestRepository;
import com.lotrel.ltserwer.authModule.infrastructure.oauth2.service.OAuth2AuthenticationFailureHandler;
import com.lotrel.ltserwer.authModule.infrastructure.oauth2.service.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig  {

    private final CustomOAuth2UserService customOAuth2UserService;

    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
            .formLogin().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint()).and()
            .authorizeHttpRequests()
                .requestMatchers("/auth/*", "/oauth2/*", "/login/oauth2/code/google", "/oauth2/callback/*")
                    .permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/index.html")
                    .permitAll()
                .requestMatchers("/swagger-ui/*", "/v3/api-docs/swagger-config", "/v3/api-docs")
                    .permitAll()
                .anyRequest()
                    .authenticated().and()
            .oauth2Login()
                .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
                .and()
                .authorizationEndpoint()
                    .baseUri("/oauth2/authorize")
                    .authorizationRequestRepository(cookieAuthorizationRequestRepository()).and()
                .userInfoEndpoint()
                    .userService(customOAuth2UserService).and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

            http.addFilterBefore(tokenAuthenticationFilter(), OAuth2LoginAuthenticationFilter.class);

        return http.build();
    }
}