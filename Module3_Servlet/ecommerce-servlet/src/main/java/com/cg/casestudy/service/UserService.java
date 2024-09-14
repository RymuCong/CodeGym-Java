package com.cg.casestudy.service;

import com.cg.casestudy.entity.User;

import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    User getByEmailPassword(String userEmail, String userPassword);

    User getById(int userId);

    List<User> getAllUsers();

    void deleteUser(int userId);

    void updateUser(User user);

    void updateUserPasswordByEmail(String userEmail, String userPassword);

    void updateUserAddress(User user);

    int userCount();

    String getAddress(int userId);

    String getEmail(int userId);

    String getPhone(int userId);

    List<String> getAllUserEmails();
}
