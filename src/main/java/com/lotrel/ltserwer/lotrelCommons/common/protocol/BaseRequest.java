package com.lotrel.ltserwer.lotrelCommons.common.protocol;

import lombok.Data;

import java.security.Principal;

@Data
public class BaseRequest {

    private Principal principal;
}
