package com.project.projetdatauser.services;

import com.project.projetdatauser.model.Link;
import com.project.projetdatauser.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public void addLinkToUser(Link link){
        linkRepository.save(link);
    }

    public Link getLinkById(String linkId){
        return linkRepository.findOneByLinkId(linkId);
    }

    public List<Link> getAllLinksByUserId (String userId){
        return linkRepository.findAllByUserId(userId);
    }

}
