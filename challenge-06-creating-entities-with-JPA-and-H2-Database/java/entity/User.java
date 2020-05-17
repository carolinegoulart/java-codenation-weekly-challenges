package com.challenge.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(max=100)
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @Email
    @Size(max=100)
    private String email;

    @NotNull
    @Size(max=50)
    private String nickname;

    @Size(max=255)
    @NotNull
    private String password;

    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    @OneToMany(mappedBy = "user")
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "user")
    private List<Submission> submissions;

    public User() { }

}
