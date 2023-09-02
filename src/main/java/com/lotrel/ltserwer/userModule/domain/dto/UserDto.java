package com.lotrel.ltserwer.userModule.domain.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String username;
    private String email;
    private UserSettingsDto userSettings;
    private UserDetailsDto userDetails;
    private Boolean accountActive;

}
