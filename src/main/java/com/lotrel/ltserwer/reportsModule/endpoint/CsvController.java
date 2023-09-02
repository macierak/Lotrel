package com.lotrel.ltserwer.reportsModule.endpoint;

import com.lotrel.ltserwer.reportsModule.domain.csv.CsvProjectRequest;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvSprintRequest;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvUserTasksRequest;
import com.lotrel.ltserwer.reportsModule.service.csv.CsvDispatcher;
import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class CsvController {

    private final CsvDispatcher csvDispatcher;
    private final Gson gson = new Gson();

    @PostMapping("api/csv/sprint")
    public ResponseEntity<String> createCsvSprint(@Valid @RequestBody CsvSprintRequest request, Principal principal) {
        request.setPrincipal(principal);
        return new ResponseEntity<>(gson.toJson("message: " + csvDispatcher.visit(request)), HttpStatus.CREATED);
    }

    @PostMapping("api/csv/project")
    public ResponseEntity<String> createCsvProject(@Valid @RequestBody CsvProjectRequest request, Principal principal) {
        request.setPrincipal(principal);
        return new ResponseEntity<>(gson.toJson("message: " + csvDispatcher.visit(request)), HttpStatus.CREATED);
    }

    @PostMapping("api/csv/user-tasks")
    public ResponseEntity<String> createCsvUserTasks(@Valid @RequestBody CsvUserTasksRequest request, Principal principal) {
        request.setPrincipal(principal);
        return new ResponseEntity<>(gson.toJson("message: " + csvDispatcher.visit(request)), HttpStatus.CREATED);
    }
}
