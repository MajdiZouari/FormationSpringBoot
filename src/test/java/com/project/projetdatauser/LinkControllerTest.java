package com.project.projetdatauser;

import com.jayway.restassured.RestAssured;
import com.project.projetdatauser.model.Link;
import com.project.projetdatauser.services.LinkService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LinkControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private LinkService linkService;

    private static Link link1;
    private static Link link2;
    private static String idLink1, idLink2;

    @BeforeClass
    public static void setUpBeforeClass() {
        RestAssured.baseURI = "http://localhost/api/v1/links";
        RestAssured.port = 8080;
    }

    @Before
    public void setUp(){
        linkService.deleteAll();
        link1 = new Link("link1","shortLink","userId",new Date(2019-01-01));
        link2 = new Link("link2","shortLink","userId",new Date(2019-02-02));
        idLink1 = linkService.addLinkToUser(link1);
        idLink2 = linkService.addLinkToUser(link2);
        link1.setLinkId(idLink1);
        link2.setLinkId(idLink2);
    }

    @Test
    public void should_success_addLinkToUser(){
        Map<String,String> link = new HashMap<>();
        link.put("link", "link");
        link.put("shortLink", "shortLink");
        link.put("userId", "userId");
        link.put("createDate", "1970-01-01T00:00:02.017+0000");

        given()
                .port(port)
                .contentType("application/json")
                //.content(objectMapper.writeValueAsString(link)))
                .body(link)
                .when().post(baseURI).then()
                .statusCode(201);
    }

    @Test
    public void should_fail_addLinkToUser(){
        Map<String,String> link = new HashMap<>();
        link.put("shortLink", "shortLink");
        link.put("createDate", "1970-01-01T00:00:02.017+0000");

        given()
                .port(port)
                .contentType("application/json")
                .body(link)
                .when().post(baseURI).then()
                .statusCode(400);
    }

    @Test
    public void should_success_getAllLinksByUserId(){
        given()
                .port(port).when().get("/user/userId")
                .then().statusCode(200)
                .assertThat()
                .body("[0].linkId", equalTo(idLink1))
                .body("[1].linkId", equalTo(idLink2));
    }

    @Test
    public void should_fail_getAllLinksByUserId(){
        linkService.deleteAll();
        given()
                .port(port).when().get("/user/userId")
                .then().statusCode(404);
    }

    @Test
    public void should_success_getLinkById(){
        Link result = given()
                .port(port).when().get("/{idLink1}", idLink1)
                .then().statusCode(200)
                .assertThat()
                .body("linkId", equalTo(idLink1))
                .extract().as(Link.class);

        assertThat(result.getLink()).isEqualTo(link1.getLink());
        assertThat(result).isEqualToComparingFieldByFieldRecursively(link1);
    }

    @Test
    public void should_fail_getLinkById(){
        given()
                .port(port).when().get("/wrongLink")
                .then().statusCode(404);
    }
}
