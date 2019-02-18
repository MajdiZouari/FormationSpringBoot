package com.project.projetdatauser.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "links")
public class Link {

    @Id
    private String linkId;
    @NotNull
    private String link;
    private String shortLink;
    @NotNull
    private String userId;
    private Date createDate;

    public Link(){

    }

    public Link(String link, String shortLink, String userId, Date createDate) {
        this.link = link;
        this.shortLink = shortLink;
        this.userId = userId;
        this.createDate = createDate;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
