package com.project.software.camgro.Camgro.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.auth.LoginRequest;
import com.project.software.camgro.Camgro.domain.*;
import com.project.software.camgro.Camgro.records.SavePostRequest;
import com.project.software.camgro.Camgro.repositories.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PostAccountsRepository postAccountsRepository;
    @Test
    public void PostController_GetAllPost_ReturnOkAll() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/post/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void PostaController_GetAllPostsByEmail_ReturnAllPostEmailOk() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/post/all/TESTING@123.com")
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void PostController_GetPostByID_ReturnPostIDOk() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        Post post = new Post("PO100", place, 10, 10, "TESTING", "TESTING",
                LocalDate.now(), LocalDate.now(), "TESTING", "TESTING", true);

        Path path = Paths.get("src/pruebazanahoria.jpeg");

        byte[] content = Files.readAllBytes(path);

        Image image = new Image("I100", post, content, "pruebazanahoria.jpeg", LocalDate.now());
        postRepository.save(post);

        imageRepository.save(image);

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/post/get/100")
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void PostController_GetOwnerPost_ReturnOwnerOk() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        Post post = new Post("PO100", place, 10, 10, "TESTING", "TESTING",
                LocalDate.now(), LocalDate.now(), "TESTING", "TESTING", true);

        Path path = Paths.get("src/pruebazanahoria.jpeg");

        byte[] content = Files.readAllBytes(path);

        Image image = new Image("I100", post, content, "pruebazanahoria.jpeg", LocalDate.now());
        postRepository.save(post);

        imageRepository.save(image);

        postAccountsRepository.save(new PostAccounts(new PostsAccountsPrimaryKey(account, post), true));

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/post/getOwner/100")
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void PostController_GetPostsSavedAccount_ReturnPostsSavedOk() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        Post post = new Post("PO100", place, 10, 10, "TESTING", "TESTING",
                LocalDate.now(), LocalDate.now(), "TESTING", "TESTING"D, true);

        Path path = Paths.get("src/pruebazanahoria.jpeg");

        byte[] content = Files.readAllBytes(path);

        Image image = new Image("I100", post, content, "pruebazanahoria.jpeg", LocalDate.now());
        postRepository.save(post);

        imageRepository.save(image);

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/post/savedPosts/TESTING@123.com")
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }

    @Test
    public void PostController_SavedPost_ReturnSavedPostOk() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();
        Account account1 = Account.builder().id("AC101").email("TESTING1@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();
        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);
        accountRepository.save(account1);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        ).andReturn();

        AuthResponse authResponse = objectMapper.readValue(response.getResponse().getContentAsString(), AuthResponse.class);

        Post post = new Post("PO100", place, 10, 10, "TESTING", "TESTING",
                LocalDate.now(), LocalDate.now(), "TESTING", "TESTING", true);

        Path path = Paths.get("src/pruebazanahoria.jpeg");

        byte[] content = Files.readAllBytes(path);

        Image image = new Image("I100", post, content, "pruebazanahoria.jpeg", LocalDate.now());
        postRepository.save(post);

        imageRepository.save(image);

        SavePostRequest savePostRequest = new SavePostRequest("TESTING1@123.com", "100");

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.get("/post/savedPosts/TESTING@123.com")
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );

    }
}
