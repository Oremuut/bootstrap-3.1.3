package ru.kata.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.bootstrap.service.UserService;
import ru.kata.bootstrap.models.User;
import ru.kata.bootstrap.service.RoleService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @GetMapping("/find")
    @ResponseBody
    public User find(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(User user) {
        userService.deleteUser(user);
        return "redirect:/admin";
    }

}
