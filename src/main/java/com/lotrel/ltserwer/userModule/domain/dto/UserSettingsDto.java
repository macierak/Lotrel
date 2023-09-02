package com.lotrel.ltserwer.userModule.domain.dto;

import lombok.Data;

@Data
public class UserSettingsDto {

    //TermsOfUseConsent
    private Boolean A1Consent;

    //UseOfDataConsent
    private Boolean A2Consent;

    //NewsletterConsent
    private Boolean B1Consent;

    private Boolean accountVisibility;
}
