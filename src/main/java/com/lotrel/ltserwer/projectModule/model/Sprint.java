package com.lotrel.ltserwer.projectModule.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;

    private OffsetDateTime beginDate;
    private OffsetDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private boolean active;

    private boolean deleted;

}
