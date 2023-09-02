package com.lotrel.ltserwer.projectModule.model;

import com.lotrel.ltserwer.projectModule.domain.enumeration.TaskEditType;
import com.lotrel.ltserwer.userModule.model.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTaskEdits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Users reporter;

    @ManyToOne
    private Task task;

    private OffsetDateTime editTime;

    private TaskEditType editType;

    private String newValue;

    private String prevValue;

}
