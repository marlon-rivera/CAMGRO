package com.project.software.camgro.Camgro.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.auth.LoginRequest;
import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.domain.Role;
import com.project.software.camgro.Camgro.records.ModifyPersonRequest;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
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

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class PersonControllerTests {

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

    @Test
    public void PersonController_FindEmail_ReturnPersonByEmail() throws Exception {
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
                MockMvcRequestBuilders.get("/person/search/TESTING@123.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + authResponse.getToken())
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void PersonController_Modify_ReturnOkModifyPerson() throws Exception {
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

        ModifyPersonRequest modifyPersonRequest = new ModifyPersonRequest(account.getEmail(), person.getName(),
                person.getPhone(), "ADDRESS MODIFIED", place.getNamePlace(), "TESTING MODIFIED");

        ResultActions results = mockMvc.perform(
                MockMvcRequestBuilders.post("/person/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + authResponse.getToken())
                        .content(objectMapper.writeValueAsString(modifyPersonRequest))
        );

        results.andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

}
