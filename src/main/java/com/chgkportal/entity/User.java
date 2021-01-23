package com.chgkportal.entity;

import com.chgkportal.model.Role;
import com.chgkportal.model.Status;
import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "user")
@Lazy
public class User {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
    @Valid
    private UserProfile userProfile;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email",
            insertable = false,
            updatable = false)
    @Email(message = "Пожалуйста, введите корректный адрес электронной почты")
    @NotBlank(message = "Пожалуйста, введите адрес электронной почты")
    private String email;

    @Column(name = "password")
    @Size(min = 8, max = 20, message = "Ваш пароль должен быть длиной от 8 до 20 символов")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "unique_code")
    private String uniqueCode;

    public User() {
    }

}
