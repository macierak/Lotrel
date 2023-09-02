package com.lotrel.ltserwer.projectModule.model;

import com.lotrel.ltserwer.projectModule.domain.enumeration.Roles;
import com.lotrel.ltserwer.userModule.model.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Map;
import java.util.Set;

@Entity
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //Core fields
    @Column(unique = true)
    private String projectName;

    @Column(unique = true)
    private String projectKey;

    //Relations
    @ManyToOne
    private Users owner;

    @ElementCollection
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @MapKeyColumn(name = "user_id")
    private Map<Users, Roles> users;

    @OneToOne
    private Sprint backlogSprint;

    private boolean active;

}
