package com.project.projetdatauser.controller;

import com.project.projetdatauser.model.User;
import com.project.projetdatauser.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Void> create(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User To Create Infos Not Good");
        userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <User> read(@PathVariable String id)  {
        User usr = userService.getUserById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Read");
        return new ResponseEntity<User>(usr, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <User> update(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User To Update Infos Not Good");
        userService.updateUser(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        User usr = userService.getUserById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Delete");
        userService.deleteUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/authentificate", method = RequestMethod.POST)
    public  ResponseEntity <?> authentificate(@RequestParam String login, @RequestParam String pwd)  {
        User usr = userService.authentificate(login,pwd);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Authentificate");
        return new ResponseEntity<User>(usr, HttpStatus.OK);
    }
}
