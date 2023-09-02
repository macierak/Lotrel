package com.lotrel.ltserwer.reportsModule.service.csv;

import com.lotrel.ltserwer.reportsModule.domain.csv.CsvProjectResponse;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvSprintResponse;
import com.lotrel.ltserwer.reportsModule.domain.csv.CsvUserTasksResponse;
import com.lotrel.ltserwer.reportsModule.domain.mail.MailReportRequest;
import com.lotrel.ltserwer.reportsModule.domain.report.ReportSprintResponse;
import com.lotrel.ltserwer.reportsModule.domain.report.ReportTaskResponse;
import com.lotrel.ltserwer.reportsModule.infrastructure.exception.FileNotCreatedException;
import com.lotrel.ltserwer.reportsModule.infrastructure.mapper.CsvMapper;
import com.lotrel.ltserwer.reportsModule.service.mail.MailVisitor;
import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CsvService {
    private final MailVisitor mailVisitor;

    private static final String PATH_SPRINT = Path.of("." + File.separator + "files") + File.separator + "report" + File.separator + "sprint" + File.separator;
    private static final String PATH_PROJECT = Path.of("." + File.separator + "files") + File.separator + "report" + File.separator + "project" + File.separator;


    public Map<String, String> createCsvSprint(CsvSprintResponse response, String mailTo, String username) throws IOException {
        final List<String[]> data = generateDataSprint(response);

        final String name = "Sprint_" + response.getDescription().replace(' ', '-') + "_" + OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm.ss"));
        final String path = PATH_SPRINT + name + ".csv";
        File csvFile = new File(path);

        if (!csvFile.exists()) {
            Files.createDirectories(Paths.get(PATH_SPRINT));
            csvFile = new File(path);
        }

        try (PrintWriter printWriter = new PrintWriter(csvFile, StandardCharsets.UTF_8)) {
            data.stream()
                    .map(this::convertToCsv)
                    .forEach(printWriter::println);
        }

        if (!csvFile.exists()) {
            throw new FileNotCreatedException(name);
        }

        sendMailWithReport(mailTo, username, name, path);
        return Map.of(name, path);
    }

    public Map<String, String> createCsvProject(CsvProjectResponse response, String mailTo, String username) throws IOException {
        final List<String[]> data = generateDataProject(response);

        final String name = "Project_" + response.getName().replace(' ', '-') + "_" + OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm.ss"));
        final String path = PATH_PROJECT + name + ".csv";
        File csvFile = new File(path);

        if (!csvFile.exists()) {
            Files.createDirectories(Paths.get(PATH_PROJECT));
            csvFile = new File(path);
        }

        try (PrintWriter printWriter = new PrintWriter(csvFile, StandardCharsets.UTF_8)) {
            data.stream()
                    .map(this::convertToCsv)
                    .forEach(printWriter::println);
        }

        if (!csvFile.exists()) {
            throw new FileNotCreatedException(name);
        }

        sendMailWithReport(mailTo, username, name, path);
        return Map.of(name, path);
    }

    public Map<String, String> createCsvUserTasks(CsvUserTasksResponse response, String mailTo, String username) throws IOException {
        final List<String[]> data = generateDataUserTasks(response);
        final String name = "UserTasks_" + response.getUsername().replace(' ', '-') + "_" + OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy_HH.mm.ss"));

        final String path = PATH_PROJECT + name + ".csv";
        File csvFile = new File(path);

        if (!csvFile.exists()) {
            Files.createDirectories(Paths.get(PATH_PROJECT));
            csvFile = new File(path);
        }

        try (PrintWriter printWriter = new PrintWriter(csvFile, StandardCharsets.UTF_8)) {
            data.stream()
                    .map(this::convertToCsv)
                    .forEach(printWriter::println);
        }

        if (!csvFile.exists()) {
            throw new FileNotCreatedException(name);
        }

        sendMailWithReport(mailTo, username, name, path);
        return Map.of(name, path);
    }

    private List<String[]> generateDataSprint(CsvSprintResponse response) {
        final String[] headerSprint = {"ID", "NAZWA SPRINTU", "DATA ROZPOCZĘCIA", "DATA ZAKOŃCZENIA", "AKTYWNY"};
        final String[] dataSprint = {String.valueOf(response.getId()), response.getDescription(), String.valueOf(response.getBeginDate()), String.valueOf(response.getEndDate()), mapActiveValue(response.isActive())};
        final String[] headerTasks = {"KOD ZADANIA", "NAZWA ZADANIA", "PRZYPISANY", "TYP", "STATUS"};
        final List<String[]> dataTasks = new ArrayList<>();
        for (ReportTaskResponse task: response.getTasks()) {
            dataTasks.add(new String[]{task.getTaskId(), task.getTaskName(), task.getAssignee(), task.getUrgencyType().name(), task.getCurrentWorkflow().name()});
        }

        final List<String[]> data = new ArrayList<>(List.of(headerSprint, dataSprint, Strings.toStringArray(Collections.emptyList()), headerTasks));
        data.addAll(dataTasks);

        return data;
    }

    private List<String[]> generateDataProject(CsvProjectResponse response) {
        final String[] headerProject = {"ID", "NAZWA PROJEKTU", "AKTYWNY"};
        final String[] dataProject = {String.valueOf(response.getId()), response.getName(), mapActiveValue(response.isActive())};
        final List<String[]> dataSprints = new ArrayList<>();
        for (ReportSprintResponse sprint: response.getSprints()) {
            dataSprints.add(Strings.toStringArray(Collections.emptyList()));
            dataSprints.addAll(generateDataSprint(CsvMapper.INSTANCE.map(sprint)));
        }

        final List<String[]> data = new ArrayList<>(List.of(headerProject, dataProject));
        data.addAll(dataSprints);

        return data;
    }

    private List<String[]> generateDataUserTasks(CsvUserTasksResponse response) {
        final String[] user = {response.getUsername(), "Aktywny -> ", mapActiveValue(response.isActive())};
        final String[] headerTasks = {"KOD ZADANIA", "NAZWA ZADANIA", "PRZYPISANY", "TYP", "STATUS"};
        final List<String[]> dataTasks = new ArrayList<>();
        for (ReportTaskResponse task: response.getTasks()) {
            dataTasks.add(new String[]{task.getTaskId(), task.getTaskName(), task.getAssignee(), task.getUrgencyType().name(), task.getCurrentWorkflow().name()});
        }

        final List<String[]> data = new ArrayList<>(List.of(user, Strings.toStringArray(Collections.emptyList()), headerTasks));
        data.addAll(dataTasks);

        return data;
    }

    private static String mapActiveValue(boolean active) {
        if (active) return "TAK";
        else return "NIE";
    }

    private String convertToCsv(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    private void sendMailWithReport(String mailTo, String username, String name, String path) {
        final MailReportRequest request = new MailReportRequest(mailTo, username, name, path);
        request.accept(mailVisitor);
    }
}
