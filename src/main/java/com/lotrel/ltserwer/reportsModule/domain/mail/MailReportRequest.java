package com.lotrel.ltserwer.reportsModule.domain.mail;

import com.lotrel.ltserwer.reportsModule.service.mail.MailVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MailReportRequest implements MailRequest {
    private String mailTo;
    private String username;
    private String report;
    private String fileLocation;

    @Override
    public String accept(MailVisitor visitor) {
        return visitor.visit(this);
    }
}
