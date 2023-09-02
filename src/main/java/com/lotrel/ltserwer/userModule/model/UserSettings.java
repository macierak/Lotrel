package com.lotrel.ltserwer.userModule.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //TermsOfUseConsent
    private Boolean A1Consent;

    //UseOfDataConsent
    private Boolean A2Consent;

    //NewsletterConsent
    private Boolean B1Consent;

    private Boolean accountVisibility;

}
