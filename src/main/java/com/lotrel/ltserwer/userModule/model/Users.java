package com.lotrel.ltserwer.userModule.model;

import com.lotrel.ltserwer.userModule.domain.enumeration.AuthProvider;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;
    private String email;

    private String password;
    private String userPortalToken;

    @OneToOne
    private UserSettings userSettings;

    @OneToOne
    private UserDetails userDetails;

    private Boolean accountActive;

    @Enumerated(value = EnumType.STRING)
    private AuthProvider provider;

}
