package com.lotrel.ltserwer.projectModule.repository;

import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

    Optional<Sprint> findByIdAndDeletedFalse(long id);

    @Query(value = "SELECT s FROM Sprint s " +
            "WHERE (s.beginDate <= :dateFrom AND s.endDate >= :dateFrom)" +
            "OR (s.beginDate >= :dateFrom AND s.beginDate <= :dateTo)" +
            "AND s.deleted = false")
    List<Sprint> findAllByDatesRange(@Param("dateFrom") OffsetDateTime dateFrom, @Param("dateTo") OffsetDateTime dateTo);

    List<Sprint> findAllByProjectIdAndDeletedIsFalse(Long projectId);
}
