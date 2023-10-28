package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.domain.*;
import com.project.software.camgro.Camgro.records.ResponseAllPosts;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.ImageRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PostRepository;
import com.project.software.camgro.Camgro.services.ImageService;
import com.project.software.camgro.Camgro.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final ImageRepository imageRepository;
    private final PostService postService;
    private final AccountRepository accountRepository;
    private final ImageService imageService;
    private final PersonRepository personRepository;
    private final PostRepository postRepository;

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPost(@RequestPart("name") String name, @RequestPart("description") String description, @RequestPart("price") String price, @RequestPart("unit") String unit, @RequestPart("postDate") String postDate, @RequestPart("harvestDate") String harvestDate, @RequestPart("quantity") String quantity, @RequestPart("postState") String postState, @RequestPart("image") MultipartFile image, @RequestPart("id_person") String id_person){
        double priceDouble = Double.parseDouble(price);
        int quantityInt = Integer.parseInt(quantity);
        LocalDate postDateDate = LocalDate.parse(postDate);
        LocalDate harvestDateDate = LocalDate.parse(harvestDate);
        Optional<Person> person = personRepository.findById(id_person);
        Optional<Account> account = accountRepository.findByPerson(person.get());
        try {
            Post post = new Post(postService.getNewId(), account.get(), account.get().getPerson().getPlace(), priceDouble, quantityInt, name, description, postDateDate, harvestDateDate, unit, postState);
            Image imageSend = new Image(imageService.getNewId(), post, image.getBytes(), image.getName(), postDateDate);
            postRepository.save(post);
            imageRepository.save(imageSend);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(new ErrorMesage("No se pudo subir la publicacion"));
        }

        return ResponseEntity.ok(new ErrorMesage("Publicacion guardada correctamente."));
    }

    @GetMapping(value = "all/{email}")
    public ResponseEntity<?> getAllPosts(@PathVariable("email") String email){
        Account account = accountRepository.findAccountByEmail(email).get();
        Optional<List<Post>> posts = postRepository.findAllByAccount(account);
        List<ResponseAllPosts> allPosts = new ArrayList<>();
        for (Post post:
             posts.get()) {
            List<Image> images = imageRepository.findAllByPost(post).get();

            allPosts.add(new ResponseAllPosts(post.getIdPost().substring(2), post.getAmountProducts(), post.getDescriptionPost(), post.getPostStatus(), post.getPostTitle(), post.getPriceProduct(), post.getHarvestDate(), post.getPublicationDate(),images.get(0).getUrl(), post.getMeasureUnit(), post.getPlace()));
        }
        return ResponseEntity.ok(allPosts);
    }

    @GetMapping(value = "/get/{numberId}")
    public ResponseEntity<?> getPost(@PathVariable("numberId") String numberId){
        Optional<Post> postOp = postRepository.findById("PO" + numberId);
        if(postOp.isPresent()){
            Post post = postOp.get();
            List<Image> images = imageRepository.findAllByPost(post).get();
            return ResponseEntity.ok(new ResponseAllPosts(post.getIdPost().substring(2), post.getAmountProducts(), post.getDescriptionPost(), post.getPostStatus(), post.getPostTitle(), post.getPriceProduct(), post.getHarvestDate(), post.getPublicationDate(),images.get(0).getUrl(), post.getMeasureUnit(), post.getPlace()));
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("Post no encontrado"));
    }

    @PostMapping(value = "modify/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> modifyPost(@PathVariable("id") String id, @RequestPart("name") String name, @RequestPart("description") String description, @RequestPart("price") String price, @RequestPart("unit") String unit, @RequestPart("postDate") String postDate, @RequestPart("harvestDate") String harvestDate, @RequestPart("quantity") String quantity, @RequestPart("postState") String postState, @RequestPart("image") String image, @RequestPart("id_person") String id_person){
        double priceDouble = Double.parseDouble(price);
        int quantityInt = Integer.parseInt(quantity);
        LocalDate postDateDate = LocalDate.parse(postDate);
        LocalDate harvestDateDate = LocalDate.parse(harvestDate);
        Optional<Person> person = personRepository.findById(id_person);
        Optional<Account> account = accountRepository.findByPerson(person.get());
        Optional<Post> postOp = postRepository.findById("PO"+id);
        System.out.println(image);
            if(postOp.isPresent()){
                System.out.println("presente imagen");
                Post post = postOp.get();
                post.setPostTitle(name);
                post.setDescriptionPost(description);
                post.setPriceProduct(priceDouble);
                post.setMeasureUnit(unit);
                post.setPublicationDate(postDateDate);
                post.setHarvestDate(harvestDateDate);
                post.setAmountProducts(quantityInt);
                post.setPostStatus(postState);
                Optional<Image> imageOp = null;

                    imageOp = imageRepository.findByUrl(Base64.getDecoder().decode(image));

                if(imageOp.isPresent()){
                    postRepository.save(post);
                }else{
                    List<Image> imagesOp = imageRepository.findAllByPost(post).get();
                    Image image1 = imagesOp.get(0);
                        image1.setUrl(Base64.getDecoder().decode(image));

                    postRepository.save(post);
                }
            }


        return ResponseEntity.ok(new ErrorMesage("Publicacion guardada correctamente."));
    }

    @GetMapping(value ="all")
    public ResponseEntity<?> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        List<ResponseAllPosts> all = new ArrayList<>();
        for (Post post :
                posts) {
            List<Image> images = imageRepository.findAllByPost(post).get();
            all.add(new ResponseAllPosts(post.getIdPost().substring(2), post.getAmountProducts(), post.getDescriptionPost(), post.getPostStatus(), post.getPostTitle(), post.getPriceProduct(), post.getHarvestDate(), post.getPublicationDate(), images.get(0).getUrl(), post.getMeasureUnit(), post.getPlace()));

        }
        return ResponseEntity.ok(all);
    }
}
