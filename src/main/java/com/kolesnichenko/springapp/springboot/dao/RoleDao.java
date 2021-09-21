package com.kolesnichenko.springapp.springboot.dao;

import com.kolesnichenko.springapp.springboot.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles();
    Role getByIdRole(int id);
}
