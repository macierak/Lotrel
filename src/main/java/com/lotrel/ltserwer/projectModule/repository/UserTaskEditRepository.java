package com.lotrel.ltserwer.projectModule.repository;

import com.lotrel.ltserwer.projectModule.domain.enumeration.TaskEditType;
import com.lotrel.ltserwer.projectModule.model.UserTaskEdits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;

public interface UserTaskEditRepository extends JpaRepository<UserTaskEdits, Long> {

    @Query(value = " SELECT e FROM UserTaskEdits e " +
                   " WHERE e.reporter.id = :assigneeId " +
                   " AND e.editType = :editType " +
                   " AND e.editTime >= :dateFrom " +
                   " AND e.editTime <= :dateTo ")
    List<UserTaskEdits> findAllByReporterAndEditTypeAndDates(@Param("assigneeId") Long assigneeId,
                                                             @Param("editType") TaskEditType editType,
                                                             @Param("dateFrom") OffsetDateTime dateFrom,
                                                             @Param("dateTo") OffsetDateTime dateTo);
}
