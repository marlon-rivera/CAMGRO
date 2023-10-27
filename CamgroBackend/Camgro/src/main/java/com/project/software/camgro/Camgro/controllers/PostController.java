package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.records.AddPostRequest;
import com.project.software.camgro.Camgro.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final ImageRepository imageRepository;

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPost(@RequestPart("post") AddPostRequest addPostRequest){
        System.out.println(addPostRequest.image().getName());
        /*try {
            byte[] bytes = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        return ResponseEntity.ok("Todo bien");
    }
}
