package com.lotrel.ltserwer.projectModule.model;

import com.lotrel.ltserwer.userModule.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
public class TaskComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Users user;

    private OffsetDateTime date;

    private String comment;

    private boolean deleted;

}
