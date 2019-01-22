package com.project.projetdatauser.controller;


import com.project.projetdatauser.model.User;
import com.project.projetdatauser.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    @RequestMapping("/api/v1/users")
    public @ResponseBody List<User> getAllUsers(){
        return (userRepository.findAll());
    }

}
