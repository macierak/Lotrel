package com.lotrel.ltserwer.reportsModule.service.csv;

import com.lotrel.ltserwer.lotrelCommons.common.CommonEntityFinder;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvProjectRequest;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvSprintRequest;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvUserTasksRequest;
import com.lotrel.ltserwer.reportsModule.infrastructure.mapper.CsvMapper;
import com.lotrel.ltserwer.reportsModule.service.ReportService;
import com.lotrel.ltserwer.userModule.model.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvDispatcher implements CsvVisitor {

    private final ReportService reportService;
    private final CsvService csvService;
    private final CommonEntityFinder commonEntityFinder;

    @Override
    public String visit(CsvSprintRequest request) {
        try {
            final Users user = commonEntityFinder.getUser(request.getPrincipal());
            final Map<String, String> file = csvService.createCsvSprint(CsvMapper.INSTANCE.map(reportService.createReportSprint(CsvMapper.INSTANCE.map(request))),
                    user.getEmail(), getName(user));
            return "Utworzono raport: " + file.keySet().stream().findFirst().orElse(null);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String visit(CsvProjectRequest request) {
        try {
            final Users user = commonEntityFinder.getUser(request.getPrincipal());
            final Map<String, String> file = csvService.createCsvProject(CsvMapper.INSTANCE.map(reportService.createReportProject(CsvMapper.INSTANCE.map(request))),
                    user.getEmail(), getName(user));
            return "Utworzono raport: " + file.keySet().stream().findFirst().orElse(null);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String visit(CsvUserTasksRequest request) {
        try {
            final Users user = commonEntityFinder.getUser(request.getPrincipal());
            final Map<String, String> file = csvService.createCsvUserTasks(CsvMapper.INSTANCE.map(reportService.createReportUserTasks(CsvMapper.INSTANCE.map(request))),
                    user.getEmail(), getName(user));
            return "Utworzono raport: " + file.keySet().stream().findFirst().orElse(null);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private String getName(Users user) {
        if (Objects.isNull(user)) return "";
        if (Objects.isNull(user.getUserDetails()) || user.getUserDetails().getFirstName().isBlank() || user.getUserDetails().getLastName().isBlank()) {
            return user.getUsername();
        }
        return user.getUserDetails().getFirstName() + " " + user.getUserDetails().getLastName();
    }

}
