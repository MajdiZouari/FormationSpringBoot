package com.project.projetdatauser.controller;

import com.project.projetdatauser.model.User;
import com.project.projetdatauser.repository.UserRepository;
import com.project.projetdatauser.services.PwdEncodingService;
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
    private UserRepository userRepository;
    @Autowired
    private PwdEncodingService pwdEncodingService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User To Create Infos Not Good");
        String encodedPwdSha256 = pwdEncodingService.PwdEncoding( user.getPwd());
        user.setPwd(encodedPwdSha256);
        userRepository.save(user);
    }

    @GetMapping(value = "/{id}")
    public User read(@PathVariable String id)  {
        User usr = userRepository.findOneById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Read");
        return usr;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User To Update Infos Not Good");
        userRepository.deleteById(user.getId());
        userRepository.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        User usr = userRepository.findOneById(id);
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Delete");
        userRepository.deleteById(id);
    }

    @RequestMapping(value = "/authentificate", method = RequestMethod.POST)
    public  @ResponseBody User authentificate(@RequestParam String login, @RequestParam String pwd)  {
        User usr = userRepository.findByLoginAndPwd(login,pwdEncodingService.PwdEncoding(pwd));
        if (usr == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To Authentificate");
        return usr;
    }
}
