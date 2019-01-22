package com.project.projetdatauser.repository;

import com.project.projetdatauser.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/*
public interface UserRepository extends CrudRepository<User, String> {

}
*/

public interface UserRepository extends MongoRepository<User, String> {

    public User findByFirstName(String firstName);
    public List<User> findAll ();

}