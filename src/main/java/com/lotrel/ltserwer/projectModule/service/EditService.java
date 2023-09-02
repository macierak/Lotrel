package com.lotrel.ltserwer.projectModule.service;

import com.lotrel.ltserwer.projectModule.domain.enumeration.EditType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.TaskEditType;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.ProjectEdits;
import com.lotrel.ltserwer.projectModule.model.Task;
import com.lotrel.ltserwer.projectModule.model.UserTaskEdits;
import com.lotrel.ltserwer.userModule.model.Users;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
public class EditService {

    public static UserTaskEdits generateEditNote(Users reporter, Task task, TaskEditType type, Object newVal, Object prevVal) {
        return UserTaskEdits.builder()
                .reporter(reporter)
                .editTime(OffsetDateTime.now())
                .editType(type)
                .task(task)
                .prevValue(Objects.isNull(prevVal) ? null : prevVal.toString())
                .newValue(Objects.isNull(newVal) ? null : newVal.toString())
                .build();
    }

    public static ProjectEdits generateEditNote(Users reporter, Project project, EditType type, Object newVal, Object prevVal) {
        return ProjectEdits.builder()
                .reporter(reporter)
                .editTime(OffsetDateTime.now())
                .editType(type)
                .project(project)
                .prevValue(Objects.isNull(prevVal) ? null : prevVal.toString())
                .newValue(Objects.isNull(newVal) ? null : newVal.toString())
                .build();
    }
}
