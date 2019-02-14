package com.project.projetdatauser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.projetdatauser.controller.UserController;
import com.project.projetdatauser.model.Adresse;
import com.project.projetdatauser.model.Email;
import com.project.projetdatauser.model.User;
import com.project.projetdatauser.services.UserService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private static User usr, usrInvalid;
    private static Email em, emInvalid;
    private static Adresse adr;

    @BeforeClass
    public static void setUpBeforeClass(){
        /*
        * *  J'ai vu dans des exemples, je dois avoir ici mes MockMvcBuilders
        * */
        adr = new Adresse("122 avenue du Général Leclerc","BOULOGNE BILLANCOURT","IDF","92100","FR");
        em = new Email("jean.dupont@yopmail.com", true);
        emInvalid = new Email("aaaaaaaaaa",true);
        usr = new User("majdi", "majdi","FR","1","Jean","Pierre","Dupont","Martin","1980-06-28", adr, em);
        usrInvalid = new User("majdi", "majdi","FR","1","Jean","Pierre","Dupont","Martin","1980-06-28", adr, emInvalid);
    }

    @Before
    public void setup() {
        usr = new User("majdi", "majdi","FR","1","Jean","Pierre","Dupont","Martin","1980-06-28", adr, em);
        userService.createUser(usr);
    }

    @Test
    public void should_success_create_user() throws Exception{
        //Given
        //Then
        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usr)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_fail_create_user() throws Exception{
        //Given
        //Then
        this.mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usrInvalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_success_get_user() throws Exception{
        //Given
        usr.setId("0000");

        Mockito.when(
                userService.getUserById(
                        Mockito.anyString())).thenReturn(usr);
        //Then
        this.mockMvc.perform(get("/api/v1/users/"+usr.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_fail_get_user() throws Exception{
        //Given
        //Then
        this.mockMvc.perform(get("/api/v1/users/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_success_update_user() throws Exception{
        //Given
        usr.setFirstName("updateFirstName");
        //Then
        this.mockMvc.perform(put("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usr)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_fail_update_user() throws Exception{
        //Given
        usr = usrInvalid;
        //Then
        this.mockMvc.perform(put("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usr)))
                .andExpect(status().isBadRequest());
    }

    /*
     * *  Not Working with status().isOk()
     * */
    @Test
    public void should_success_delete_user() throws Exception{
        //Given
        userService.createUser(usr);
        //Then
        this.mockMvc.perform(delete("/api/v1/users/{id}" , usr.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_fail_delete_user() throws Exception{
        //Given
        //Then
        this.mockMvc.perform(delete("/api/v1/users/123" )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_success_authentificate_user() throws Exception{
        //Given
        Mockito.when(
                userService.authentificate(
                        Mockito.anyString(),Mockito.anyString())).thenReturn(usr);
        //Then
        this.mockMvc.perform(post("/api/v1/users/authentificate")
                .param("login", usr.getLogin())
                .param("pwd", usr.getPwd()))
                .andExpect(status().isOk());
    }

    @Test
    public void should_fail_authentificate_user() throws Exception{
        //Given
        //Then
        this.mockMvc.perform(post("/api/v1/users/authentificate")
                .param("login", "123")
                .param("pwd", "123"))
                .andExpect(status().isNotFound());
    }
}
