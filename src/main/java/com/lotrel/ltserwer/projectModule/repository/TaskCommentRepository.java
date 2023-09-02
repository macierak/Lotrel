package com.lotrel.ltserwer.projectModule.repository;

import com.lotrel.ltserwer.projectModule.model.TaskComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskCommentRepository extends JpaRepository<TaskComment, Long> {
}
