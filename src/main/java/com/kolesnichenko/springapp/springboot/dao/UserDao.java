package com.kolesnichenko.springapp.springboot.dao;

import com.kolesnichenko.springapp.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);

}
