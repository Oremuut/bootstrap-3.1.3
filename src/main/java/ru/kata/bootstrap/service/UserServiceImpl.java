package ru.kata.bootstrap.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.bootstrap.repositories.UserRepository;
import ru.kata.bootstrap.models.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);

        if (existingUser != null && (user.getPassword() == null || user.getPassword().isEmpty())) {
            user.setPassword(existingUser.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (existingUser != null && (user.getRoles() == null || user.getRoles().isEmpty())) {
            user.setRoles(existingUser.getRoles());
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}