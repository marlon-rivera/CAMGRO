package com.project.software.camgro.Camgro.auth;

import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.auth.LoginRequest;
import com.project.software.camgro.Camgro.auth.RegisterRequest;
import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.jwt.JwtService;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {


    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final PlaceRepository placeRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private  final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
        System.out.println("Login");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        }catch (AuthenticationException e){
            System.out.println(e.getMessage());
        }

        System.out.println("No pase");
        Optional<Account> account = accountRepository.findAccountByEmail(loginRequest.getEmail());
        System.out.println(account);
        String token = jwtService.getToken(account.get());
        System.out.println("Token aca: " + token);
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        Optional<Place> place = placeRepository.findByTypeOfPlaceAndNamePlace("C", "Bogota");
        System.out.println(place.get());
        if(place.isEmpty()){
            placeRepository.save(new Place("PL01", "C", null, "Bogota"));
        }
        Person person = new Person("P01", registerRequest.getName(), registerRequest.getPhone(), registerRequest.getAddress(), placeRepository.findByNamePlace("Bogota").orElseThrow());
        Account account = new Account("AC01", person, registerRequest.getEmail(), registerRequest.getPassword());
        personRepository.save(person);
        accountRepository.save(account);
        return new AuthResponse(jwtService.getToken(account));
    }
}
