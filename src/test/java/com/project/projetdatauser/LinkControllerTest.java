package com.project.projetdatauser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.project.projetdatauser.controller.LinkController;
import com.project.projetdatauser.model.Link;
import com.project.projetdatauser.repository.LinkRepository;
import com.project.projetdatauser.services.LinkService;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LinkControllerTest {


    @LocalServerPort
    private int port;
/*
    private static Link link1;
    private static Link link2;

    private static String idLink1, idLink2;

*/
    @BeforeClass
    public static void setup() {
        /*linkService.deleteAll();
        link1 = new Link("link1","shortLink","userId",new Date(2019-01-01));
        link2 = new Link("link2","shortLink","userId",new Date(2019-02-02));
        idLink1 = linkService.addLinkToUser(link1);
        idLink2 = linkService.addLinkToUser(link2);*/
        RestAssured.baseURI = "http://localhost/api/v1/links";
        RestAssured.port = 8080;
    }
/*
    @Before
    public  void  setUp(){
        linkService.deleteAll();
        link1 = new Link("link1","shortLink","userId",new Date(2019-01-01));
        link2 = new Link("link2","shortLink","userId",new Date(2019-02-02));
        idLink1 = linkService.addLinkToUser(link1);
        idLink2 = linkService.addLinkToUser(link2);
        link1.setLinkId(idLink1);
        link2.setLinkId(idLink2);
    }
*/
    @Test
    public void should_success_getAllLinksByUserId(){
        given().port(port).when().get("/user/userId")
                .then().statusCode(200);
    }

    @Test
    public void should_success_getLinkById(){
        given().port(port).when().get("/5c6c5da5acac3758586857b7").then().statusCode(200).assertThat()
                .body("linkId", equalTo("5c6c5da5acac3758586857b7"));

    }
}
