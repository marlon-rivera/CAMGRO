package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.ErrorMesage;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.records.ModifyPersonRequest;
import com.project.software.camgro.Camgro.records.SearchPersonRequest;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import com.project.software.camgro.Camgro.services.PersonService;
import com.project.software.camgro.Camgro.services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;
    private final PlaceRepository placeRepository;
    private final PlaceService placeService;


    @GetMapping(value ="search/{email}")
    public ResponseEntity<?> searchPerson(@PathVariable("email") String email){
        System.out.println(email);
        Optional<Account> account = accountRepository.findAccountByEmail(email);
        if(account.isPresent()) {
            return ResponseEntity.ok(account.get().getPerson());
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("Cuenta no encontrada."));

    }

    @PostMapping(value = "modify")
    public ResponseEntity<?> modifyAccount(@RequestBody ModifyPersonRequest modifyPersonRequest) {
        Optional<Account> account = accountRepository.findAccountByEmail(modifyPersonRequest.email());
        if (account.isPresent()) {
            Optional<Person> person = personRepository.findById(account.get().getPerson().getId());
            System.out.println(modifyPersonRequest.department());
            System.out.println(modifyPersonRequest.city());
            Optional<Place> placeDep = placeRepository.findByTypeOfPlaceAndNamePlace("D", modifyPersonRequest.department());
            Optional<Place> placeCit;
            Place city;
            if(placeDep.isPresent()){
                placeCit = placeRepository.findByTypeOfPlaceAndNamePlace("C", modifyPersonRequest.city());
                city = placeCit.orElseGet(() -> placeRepository.save(new Place(placeService.getNewId(), "C", placeDep.get(), modifyPersonRequest.city())));
            }else{
                Place dep = placeRepository.save(new Place(placeService.getNewId(), "D", null, modifyPersonRequest.department()));
                city = placeRepository.save(new Place(placeService.getNewId(), "C", dep, modifyPersonRequest.city()));
            }
            Person aux = person.get();
            aux.setPlace(city);
            aux.setName(modifyPersonRequest.name());
            aux.setPhone(modifyPersonRequest.phone());
            aux.setAddress(modifyPersonRequest.address());
            personRepository.save(aux);
        }
        return ResponseEntity.ok(new ErrorMesage("La informacion se ha actualizado correctamente."));
    }

}
