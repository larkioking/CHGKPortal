package com.chgkportal.entity;

import com.chgkportal.model.Role;
import com.chgkportal.model.Status;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@Lazy
public class User {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    private UserProfile userProfile;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "email",
            insertable = false,
            updatable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public User() {
    }

}
