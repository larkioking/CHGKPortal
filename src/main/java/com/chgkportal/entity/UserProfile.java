package com.chgkportal.entity;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "user_social")
@Lazy
public class UserProfile {

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private User user;

    @Id
    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "vk_link")
    private String vkLink;

    @Column(name = "fb_link")
    private String fbLink;

    @Column(name = "tw_link")
    private String twLink;

    @Column(name = "tg_link")
    private String tgLink;

}
