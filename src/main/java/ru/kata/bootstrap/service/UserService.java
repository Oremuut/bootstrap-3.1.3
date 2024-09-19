package ru.kata.bootstrap.service;

import ru.kata.bootstrap.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);

    void saveUser(User user);

    void deleteUser(User user);
}
