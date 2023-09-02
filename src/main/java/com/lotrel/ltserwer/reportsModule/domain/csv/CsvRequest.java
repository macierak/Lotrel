package com.lotrel.ltserwer.reportsModule.domain.csv;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lotrel.ltserwer.reportsModule.domain.mail.MailReportRequest;
import com.lotrel.ltserwer.reportsModule.service.csv.CsvVisitor;
import com.lotrel.ltserwer.reportsModule.service.mail.MailVisitor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CsvSprintRequest.class, name ="CsvSprint")
})
public interface CsvRequest {
    void accept(CsvVisitor visitor);
}
