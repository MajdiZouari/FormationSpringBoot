package com.project.projetdatauser.repository;

import com.project.projetdatauser.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findOneById(String id);
    public User findByFirstName(String firstName);
    public List<User> findAll ();
    public void deleteById(String id);
    public User findByLogin(String login);

}