package com.chgkportal.service;

import com.chgkportal.entity.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {

    public Optional<UserProfile> findAll();

    public UserProfile findByEmail(String email);

    public void save(UserProfile userProfile);

    public void deleteByEmail(String email);

}
