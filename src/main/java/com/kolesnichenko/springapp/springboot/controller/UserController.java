package com.kolesnichenko.springapp.springboot.controller;

import com.kolesnichenko.springapp.springboot.model.Role;
import com.kolesnichenko.springapp.springboot.model.User;
import com.kolesnichenko.springapp.springboot.service.RoleService;
import com.kolesnichenko.springapp.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin")
    public String showUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getUsers());
        return "admin";
    }

    @PostMapping("admin/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("role") ArrayList<Integer> role) {
        user.setRoles(role.stream().map((r) -> roleService.getByIdRole(r)).collect(Collectors.toSet()));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("role") ArrayList<Integer> role) {
        user.setRoles(role.stream().map((r) -> roleService.getByIdRole(r)).collect(Collectors.toSet()));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("user")
    public String getUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "user";

    }
    @GetMapping("login")
    public String loginPage() {
        return "login";
    }
}
