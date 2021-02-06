package com.chgkportal.repository;

import com.chgkportal.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    Optional<UserProfile> findAllByOrderByEmail();

    Optional<UserProfile> findByEmail(String email);

    void deleteByEmail(String email);
}
