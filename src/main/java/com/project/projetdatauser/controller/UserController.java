package com.project.projetdatauser.controller;

import com.project.projetdatauser.model.User;
import com.project.projetdatauser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User To Create Infos Not Good");
        userService.createUser(user);
    }

    @GetMapping(value = "/{id}")
    public User read(@PathVariable String id)  {
        User usr = userService.getUserById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Read");
        return usr;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User To Update Infos Not Good");
        userService.updateUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        User usr = userService.getUserById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Delete");
        userService.deleteUserById(id);
    }

    @RequestMapping(value = "/authentificate", method = RequestMethod.POST)
    public  @ResponseBody User authentificate(@RequestParam String login, @RequestParam String pwd)  {
        User usr = userService.authentificate(login,pwd);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Authentificate");
        return usr;
    }
}
