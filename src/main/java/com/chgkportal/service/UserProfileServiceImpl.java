package com.chgkportal.service;

import com.chgkportal.entity.UserProfile;
import com.chgkportal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public Optional<UserProfile> findAll() {
        return userProfileRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public UserProfile findByEmail(String email) {
        return userProfileRepository.findByEmail(email);
    }

    @Override
    public void save(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    @Override
    public void deleteByEmail(String email) {
        userProfileRepository.deleteByEmail(email);
    }
}