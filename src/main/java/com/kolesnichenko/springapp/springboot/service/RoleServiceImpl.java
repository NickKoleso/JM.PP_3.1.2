package com.kolesnichenko.springapp.springboot.service;

import com.kolesnichenko.springapp.springboot.dao.RoleDao;
import com.kolesnichenko.springapp.springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    @Transactional
    public Role getByIdRole(int id) {
        return roleDao.getByIdRole(id);
    }
}
