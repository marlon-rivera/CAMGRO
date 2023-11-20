package com.project.software.camgro.Camgro.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.software.camgro.Camgro.auth.LoginRequest;
import com.project.software.camgro.Camgro.auth.RegisterRequest;
import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.domain.Role;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class AuthControllerTests {

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
    public void AuthController_Loging_ReturnAccountLogged() throws Exception {
        Place place = Place.builder().idPlace("PL100").namePlace("TESTING").typeOfPlace("D").lugIdLug(null).build();
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("TESTING").place(place).build();
        Account account = Account.builder().id("AC100").email("TESTING@123.com").person(person).active(true)
                .role(Role.USER).password(encoder.encode("123")).build();

        placeRepository.save(place);
        personRepository.save(person);
        accountRepository.save(account);

        LoginRequest loginRequest = new LoginRequest("TESTING@123.com", "123");

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void AuthController_Register_ReturnAccounRegister() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("TESTING","1111111111", "TESTING", "TESTING1", "TESTING2",
                "TESTING123@123.com", "123");

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());

    }
}

