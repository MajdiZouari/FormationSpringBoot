package com.project.projetdatauser.controller;

import com.project.projetdatauser.model.User;
import com.project.projetdatauser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody User user) {
        userRepository.save(user);
    }

    @GetMapping(value = "/{id}")
    public User read(@PathVariable String id)  {
        User usr = userRepository.findOneById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Read");
        return usr;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user) {
        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        User usr = userRepository.findOneById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Delete");
        userRepository.deleteById(id);
    }
}
