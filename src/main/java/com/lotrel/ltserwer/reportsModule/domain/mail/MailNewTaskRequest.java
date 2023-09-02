package com.lotrel.ltserwer.reportsModule.domain.mail;

import com.lotrel.ltserwer.reportsModule.service.mail.MailVisitor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MailNewTaskRequest implements MailRequest {
    private String mailTo;
    private String taskName;
    private String taskCode;
    private String projectName;
    private String sprintName;

    @Override
    public String accept(MailVisitor visitor) {
        return visitor.visit(this);
    }
}
