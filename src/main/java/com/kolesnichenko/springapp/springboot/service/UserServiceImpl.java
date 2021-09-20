package com.kolesnichenko.springapp.springboot.service;

import com.kolesnichenko.springapp.springboot.dao.UserDao;
import com.kolesnichenko.springapp.springboot.model.Role;
import com.kolesnichenko.springapp.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    @Transactional
    public User findById(int id) {
        return userDao.findById(id).get();
    }

    @Override
    @Transactional
    public Iterable<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        userDao.deleteById(id);
    }


}
