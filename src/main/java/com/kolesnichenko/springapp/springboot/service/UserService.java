package com.kolesnichenko.springapp.springboot.service;

import com.kolesnichenko.springapp.springboot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.List;
@Service
public interface UserService extends UserDetailsService {
    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserById(int id);

    List<User> getUsers();

    User getUserByName(String name);


}
