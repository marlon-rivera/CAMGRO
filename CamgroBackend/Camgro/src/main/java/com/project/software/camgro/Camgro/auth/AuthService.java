package com.project.software.camgro.Camgro.auth;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.jwt.JwtService;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import com.project.software.camgro.Camgro.services.AccountService;
import com.project.software.camgro.Camgro.services.PersonService;
import com.project.software.camgro.Camgro.services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private  final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder;
    private final AccountService accountService;
    private final PlaceService placeService;
    private final PersonService personService;

    public AuthResponse login(LoginRequest loginRequest) throws UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        }catch (AuthenticationException e){
            throw new UsernameNotFoundException(e.getMessage());
        }
        Optional<Account> account = accountRepository.findAccountByEmail(loginRequest.getEmail());
        String token = jwtService.getToken(account.get());
        return new AuthResponse(token);
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        Optional<Place> placeDep = placeRepository.findByTypeOfPlaceAndNamePlace("D", registerRequest.getDepartment());
        System.out.println("Pase departamento");
        System.out.println(placeDep.get());
        //CORREGIR
        Optional<Place> placeCit;
        Place city;
        if(placeDep.isPresent()){
            placeCit = placeRepository.findByTypeOfPlaceAndNamePlace("C", registerRequest.getCity());
            System.out.println(placeService.getId());
            city = placeCit.orElseGet(() -> placeRepository.save(new Place(placeService.getNewId(), "C", placeDep.get(), registerRequest.getCity())));
            System.out.println("pase dep present");
        }else{
            Place dep = placeRepository.save(new Place(placeService.getNewId(), "D", null, registerRequest.getDepartment()));
            city = placeRepository.save(new Place(placeService.getNewId(), "C", dep, registerRequest.getCity()));
            System.out.println("pase dp no present");
        }
        Person person = new Person(personService.getNewId(), registerRequest.getName(), registerRequest.getPhone(), registerRequest.getAddress(), city);
        Account account = new Account(accountService.getNewID(), person, registerRequest.getEmail(), encoder.encode(registerRequest.getPassword()));
        personRepository.save(person);
        accountRepository.save(account);
        System.out.println("pase cuenta y person ");
        return new AuthResponse(jwtService.getToken(account));
    }
}
