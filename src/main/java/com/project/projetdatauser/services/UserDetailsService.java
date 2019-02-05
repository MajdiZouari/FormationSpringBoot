package com.project.projetdatauser.services;

import com.project.projetdatauser.exceptions.UserNotFoundException;
import com.project.projetdatauser.model.User;

public interface UserDetailsService {
    public User loadUserByLogin(String login) throws UserNotFoundException;
}
