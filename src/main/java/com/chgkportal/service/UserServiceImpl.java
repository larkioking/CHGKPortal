package com.chgkportal.service;

import com.chgkportal.entity.User;
import com.chgkportal.model.Role;
import com.chgkportal.model.Status;
import com.chgkportal.repository.UserRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailSender mailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new NonUniqueObjectException(user.getId(), user.getEmail());
        }

        user.setRole(Role.ROLE_USER);
        user.setStatus(Status.NOT_CONFIRMED);
        user.getUserProfile().setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUniqueCode(UUID.randomUUID().toString());

        String message = String.format(
                "Здравствуйте, %s! \n" +
                        "Для завершения регистрации перейдите по следующей ссылке: \n" +
                        "http://localhost:8080/registration/activate/%s",
                user.getFirstName(),
                user.getUniqueCode()
        );

        mailSender.send(user.getEmail(), "Регистрация на портале", message);
        userRepository.save(user);
    }

    @Override
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public void activateUser(String code) {
        User user = userRepository.findUserByUniqueCode(code).orElseThrow(RuntimeException::new);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }
}
