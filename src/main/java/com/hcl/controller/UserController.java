package com.hcl.controller;

import java.util.List;
import com.hcl.entity.User;
import com.hcl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String newUser(User user) {
        userService.saveUser(user);
        return ("redirect:/user/list");
    }

    @GetMapping("/add")
    public ModelAndView addNewUser() {
        return new ModelAndView("adduser", "user", new User());
    }

    @GetMapping("/update")
    public ModelAndView searchUser() {
        return new ModelAndView("search", "user", new User());
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user.getId(), user.getName(), user.getEmail(),
                user.getPassword());
        return ("redirect:/user/list");
    }

    @GetMapping("/list")
    public ModelAndView listUsers() {
        List<User> users = userService.getAllUsers();
        return new ModelAndView("listUsers", "users", users);
    }

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

}