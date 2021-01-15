package com.chgkportal.service;

import com.chgkportal.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {

    Optional<UserProfile> findAll();

    Optional<UserProfile> findByEmail(String email);

    void save(UserProfile userProfile);

    void deleteByEmail(String email);

}
