package com.lotrel.ltserwer.projectModule.model;

import com.lotrel.ltserwer.projectModule.domain.enumeration.EditType;
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
public class ProjectEdits {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Users reporter;

    private OffsetDateTime editTime;

    @ManyToOne
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(100)")
    private EditType editType;

    private String newValue;

    private String prevValue;

}
