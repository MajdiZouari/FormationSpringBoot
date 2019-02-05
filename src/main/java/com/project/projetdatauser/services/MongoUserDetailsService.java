package com.project.projetdatauser.services;

import com.project.projetdatauser.exceptions.UserNotFoundException;
import com.project.projetdatauser.model.User;
import com.project.projetdatauser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MongoUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByLogin(String login) throws UserNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }
}