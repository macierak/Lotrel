package com.lotrel.ltserwer.reportsModule.domain.mail;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lotrel.ltserwer.reportsModule.service.mail.MailVisitor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = MailReportRequest.class, name ="ReportMail")
})
public interface MailRequest {
    String accept(MailVisitor visitor);
}
