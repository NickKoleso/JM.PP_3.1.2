package com.kolesnichenko.springapp.springboot.service;


import com.kolesnichenko.springapp.springboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getByIdRole(int id);
}
