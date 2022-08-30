package com.noirix.controller;

import com.noirix.domain.User;
import com.noirix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public ModelAndView findAllUsers() {
        List<User> users = userService.findAll();

        ModelAndView model = new ModelAndView();
        model.addObject("user", "Slava");
        model.addObject("users", users);

        model.setViewName("hello");

        return model;
    }
}
