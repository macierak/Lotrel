package com.lotrel.ltserwer.projectModule.repository;

import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT t FROM Task t WHERE t.id IN :id AND t.deleted = false")
    List<Task> findAllByTaskIdAndOrderBy(@Param("id") Set<Long> ids, Sort sort);

    @Query(value = "SELECT t FROM Task t WHERE t.sprint.id = :sprintId AND t.deleted = false")
    List<Task> findAllBySprintAndOrderBy(@Param("sprintId") Long sprintId, Sort sort);

    Optional<Task> findByIdAndDeletedFalse(Long id);

    List<Task> findAllBySprintAndDeletedFalse(Sprint sprint, Sort unsorted);

    List<Task> findAllByAssigneeIdAndDeletedIsFalse(Long assigneeId);
}
