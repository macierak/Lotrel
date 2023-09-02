package com.lotrel.ltserwer.authModule.endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @GetMapping("/login/oauth2/code/google")
    public String getLoginInfo(@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {
        log.info("{}", authenticationToken);
        final OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName());
        return "Success";
    }

    @GetMapping("/oauth2/callback/google")
    public String getLoginInfo2(@AuthenticationPrincipal OAuth2AuthenticationToken authenticationToken) {
        log.info("{}", authenticationToken);
        final OAuth2AuthorizedClient client = oAuth2AuthorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName());
        return "Success";
    }

    @GetMapping("/api/auth/authChecker")
    public String checkIfIsAuthenticatedGet() {
        return "OK";
    }

    @PostMapping("/api/auth/authChecker")
    public String checkIfIsAuthenticatedPost() {
        return "OK";
    }

}
