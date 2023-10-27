package com.project.software.camgro.Camgro.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.project.software.camgro.Camgro.records.AddPostRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@RestController
@RequestMapping("/post")
public class PostController {

    @PostMapping(value = "add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addPost(@RequestPart("image") MultipartFile image){
        InputStream credentialsStream = getClass().getResourceAsStream("/nombre_del_archivo.json");
        try {
            GoogleCredential credentials = GoogleCredential.fromStream(credentialsStream)
                    .createScoped(Collections.singleton(DriveScopes.DRIVE_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            byte[] bytes = image.getBytes();
            Path path = Paths.get("C:\\Users\\Alejandro\\OneDrive\\Escritorio\\CAMGRO\\CamgroBackend\\Camgro\\src\\prueba" + image.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Todo bien");
    }
}
