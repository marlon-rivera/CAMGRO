package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.domain.*;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.ImageRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.services.AccountService;
import com.project.software.camgro.Camgro.services.ImageService;
import com.project.software.camgro.Camgro.services.PostService;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final ImageRepository imageRepository;
    private final PostService postService;
    private final AccountRepository accountRepository;
    private final ImageService imageService;
    private final PersonRepository personRepository;

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPost(@RequestPart("name") String name, @RequestPart("description") String description, @RequestPart("price") String price, @RequestPart("unit") String unit, @RequestPart("postDate") String postDate, @RequestPart("harvestDate") String harvestDate, @RequestPart("quantity") String quantity, @RequestPart("postState") String postState, @RequestPart("image") MultipartFile image, @RequestPart("id_person") String id_person){
        double priceDouble = Double.parseDouble(price);
        int quantityInt = Integer.parseInt(quantity);
        LocalDate postDateDate = LocalDate.parse(postDate);
        LocalDate harvestDateDate = LocalDate.parse(harvestDate);
        System.out.println(id_person);
        Optional<Person> person = personRepository.findById(id_person);
        System.out.println(person.get().getName());
        Optional<Account> account = accountRepository.findByPerson(person.get());

        try {
            Post post = new Post(postService.getNewId(), account.get(), account.get().getPerson().getPlace(), priceDouble, quantityInt, name, description, postDateDate, harvestDateDate, unit, postState);
            Image imageSend = new Image(imageService.getNewId(), post, image.getBytes(), image.getName(), postDateDate);
            System.out.println(imageSend.getPost().getIdPost());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(new ErrorMesage("No se pudo subir la publicacion"));
        }

        return ResponseEntity.ok("Todo bien");
    }
}
