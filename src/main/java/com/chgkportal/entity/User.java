package com.chgkportal.entity;

import com.chgkportal.model.Role;
import com.chgkportal.model.Status;
import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

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
    @Size(min = 8, message = "Ваш пароль должен быть длиной от 8 до 30 символов")
    //@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)$", message = "Ваш пароль не соответствует требованиям")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "unique_code")
    private String uniqueCode;

    @Column(name = "profile_pic")
    private String profilePicture;

    @NotBlank(message = "Пожалуйста, введите имя.")
    @Pattern(regexp = "(?<!\\S)\\p{L}+(?!\\S)", message = "Ваше имя не должно содержать цифр")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Пожалуйста, введите фамилию.")
    @Pattern(regexp = "(?<!\\S)\\p{L}+(?!\\S)", message = "Ваша фамилия не должна содержать цифр")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "country")
    @NotBlank(message = "Пожалуйста, введите страну.")
    private String country;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date", columnDefinition = "DATE")
    @Past
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Column(name = "city")
    @NotBlank(message = "Пожалуйста, введите город.")
    private String city;

    @Column(name = "mac_id")
    @Max(value = 999999, message = "Пожалуйста, проверьте правильность вашего Мак-ID.")
    private Integer macId;

    public User() {
    }

}
