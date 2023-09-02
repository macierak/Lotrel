package com.lotrel.ltserwer.reportsModule.service.mail;

import com.lotrel.ltserwer.reportsModule.domain.mail.MailAssignTaskRequest;
import com.lotrel.ltserwer.reportsModule.domain.mail.MailNewTaskRequest;
import com.lotrel.ltserwer.reportsModule.domain.mail.MailReportRequest;

public interface MailVisitor {

    String visit(MailReportRequest request);
    String visit(MailAssignTaskRequest request);
    String visit(MailNewTaskRequest request);


}
