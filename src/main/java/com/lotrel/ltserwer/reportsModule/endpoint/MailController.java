package com.lotrel.ltserwer.reportsModule.endpoint;

import com.lotrel.ltserwer.reportsModule.domain.mail.MailReportRequest;
import com.lotrel.ltserwer.reportsModule.service.mail.MailDispatcher;
import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MailController {

    private final MailDispatcher mailDispatcher;

    private final Gson gson = new Gson();
    @PostMapping("api/mail/report/sprint")
    public ResponseEntity<String> sendReportSprint(@Valid @RequestBody MailReportRequest request) {
        return new ResponseEntity<>(gson.toJson("message: " + request.accept(mailDispatcher)), HttpStatus.OK);
    }
}
