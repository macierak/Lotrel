package com.lotrel.ltserwer.projectModule.model;

import com.lotrel.ltserwer.projectModule.domain.enumeration.UrgencyType;
import com.lotrel.ltserwer.projectModule.domain.enumeration.Workflow;
import com.lotrel.ltserwer.userModule.model.Users;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //Core info
    private String taskId;

    private String taskName;

    private String taskExternalUrl;

    private boolean deleted;

    private OffsetDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Users assignee;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private Users reporter;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    private String description;

    //Relations
    @OneToMany
    private Set<TaskComment> comments;

    //Time
    private Integer totalLoggedTime;

    private Integer remainingTime;

    private Integer estimatedTime;

    //Enums
    @Enumerated(EnumType.STRING)
    private UrgencyType urgencyType;

    @Enumerated(EnumType.STRING)
    private Workflow currentWorkflow;

}
