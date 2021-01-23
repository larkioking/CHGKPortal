package com.chgkportal.entity;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "user_profile")
@Lazy
public class UserProfile {

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private User user;

    @Id
    @Column(name = "email")
    @Email
    private String email;

    @NotBlank(message = "Пожалуйста, введите имя.")
    @Pattern(regexp = "(?<!\\S)\\p{L}+(?!\\S)", message = "Ваше имя не должно содержать цифр")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Пожалуйста, введите фамилию.")
    @Pattern(regexp = "(?<!\\S)\\p{L}+(?!\\S)", message = "Ваша фамилия не должна содержать цифр")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "country")
    @NotBlank(message = "Пожалуйста, введите страну.")
    private String country;

    @Column(name = "gender")
    private String gender;

    @Column(name = "city")
    @NotBlank(message = "Пожалуйста, введите город.")
    private String city;

    @Column(name = "mac_id")
    @Max(value = 999999, message = "Пожалуйста, проверьте правильность вашего Мак-ID.")
    private Integer macId;

    @Column(name = "vk_link")
    private String vkLink;

    @Column(name = "fb_link")
    private String fbLink;

    @Column(name = "tw_link")
    private String twLink;

    @Column(name = "tg_link")
    private String tgLink;

}
