package com.kolesnichenko.springapp.springboot.service;

import com.kolesnichenko.springapp.springboot.model.Role;
import com.kolesnichenko.springapp.springboot.model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    User save(User user);

    User findById(int id);

    Iterable<User> findAll();

    void deleteById(int id);

}
