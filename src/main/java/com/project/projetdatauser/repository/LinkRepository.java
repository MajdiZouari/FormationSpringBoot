package com.project.projetdatauser.repository;

import com.project.projetdatauser.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LinkRepository  extends MongoRepository<Link, String> {
    public List<Link> findAllByUserId(String userId);
    public Link findOneByLinkId(String linkId);
}
