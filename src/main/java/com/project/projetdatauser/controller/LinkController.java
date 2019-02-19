package com.project.projetdatauser.controller;

import com.project.projetdatauser.model.Link;
import com.project.projetdatauser.services.LinkService;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1/links")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Void> addLinkToUser(@RequestBody @Valid Link link, BindingResult result){
        if (result.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Link Infos Not Valid");
        linkService.addLinkToUser(link);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(link.getLinkId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List <Link> >getAllLinksByUserId(@PathVariable String userId){
        List <Link> listLinks = linkService.getAllLinksByUserId(userId);
        if (listLinks.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found To get Links");
        return new ResponseEntity(listLinks, HttpStatus.OK);

    }
    @GetMapping(value = "/{linkId}")
    public ResponseEntity <Link> getLinkById(@PathVariable String linkId){
        Link link = linkService.getLinkById(linkId);
        if(link == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Link Not Found");
        return new ResponseEntity(link,HttpStatus.OK);
    }

}
