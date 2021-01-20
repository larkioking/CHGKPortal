package com.chgkportal.service;

import com.chgkportal.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findByEmail(String email);

    void save(User user);

    void register(User user);

    void deleteByEmail(String email);

    void activateUser(String code);
}
