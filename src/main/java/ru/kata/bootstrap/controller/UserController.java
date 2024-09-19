package ru.kata.bootstrap.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.bootstrap.models.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String user(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        boolean hasRole = auth.getAuthorities().stream().anyMatch(g -> g.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("user", user);
        model.addAttribute("hasRole", hasRole);
        return "user";
    }

}
