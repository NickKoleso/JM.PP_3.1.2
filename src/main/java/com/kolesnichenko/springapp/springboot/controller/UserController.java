package com.kolesnichenko.springapp.springboot.controller;

import com.kolesnichenko.springapp.springboot.model.Role;
import com.kolesnichenko.springapp.springboot.model.User;
import com.kolesnichenko.springapp.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(@Qualifier("userServiceImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin")
    public String showUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("listUsers", userService.findAll());
        return "admin";
    }

    @PostMapping("admin/add")
    public String addUser(@ModelAttribute("user") User user, @ModelAttribute("role") String role) {
        Role newRole = new Role((int) (role.equals("ADMIN") ? 1L : 2L), role);
        Set<Role> roles = new HashSet<>();
        roles.add(newRole);
        user.setRoles(roles);
        userService.save(user);

//        Set<Role> role = new HashSet<>();
//        role.add(new Role(newRoles));
//        user.setRoles(role);
//        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @ModelAttribute("role") String role) {
        Role newRole = new Role((int) (role.equals("ADMIN") ? 1L : 2L), role);
        Set<Role> roles = new HashSet<>();
        roles.add(newRole);
        user.setRoles(roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("user")
    public String getUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";

    }
}
