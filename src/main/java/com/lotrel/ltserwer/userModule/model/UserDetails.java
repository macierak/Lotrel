package com.lotrel.ltserwer.userModule.model;

import com.lotrel.ltserwer.authModule.infrastructure.oauth2.user.OAuth2UserInfo;
import com.lotrel.ltserwer.projectModule.model.Project;
import com.lotrel.ltserwer.projectModule.model.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String company;
    private String position;

    private String imageUrl;

    @OneToMany
    private Set<Project> projects;

    @OneToMany
    private Set<Task> lastTasks;

    public UserDetails(OAuth2UserInfo info) {
        String[] namePieces = info.getName().split(" ");
        this.firstName = namePieces[0];
        this.lastName = namePieces[namePieces.length-1];
        this.imageUrl = info.getImageUrl();

    }

}
