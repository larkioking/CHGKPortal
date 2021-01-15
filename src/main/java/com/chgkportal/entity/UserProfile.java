package com.chgkportal.entity;

import lombok.Data;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_profile")
@Lazy
public class UserProfile {

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private User user;

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "country")
    private String country;

    @Column(name = "gender")
    private String gender;

    @Column(name = "city")
    private String city;

    @Column(name = "mac_id")
    private int macId;

    @Column(name = "vk_link")
    private String vkLink;

    @Column(name = "fb_link")
    private String fbLink;

    @Column(name = "tw_link")
    private String twLink;

    @Column(name = "tg_link")
    private String tgLink;

}
