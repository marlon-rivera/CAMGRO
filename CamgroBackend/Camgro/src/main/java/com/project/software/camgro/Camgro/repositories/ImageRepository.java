package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Image;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {

    Optional<Image> findTopByOrderByIdImageDesc();

    Optional<Image> findByUrl(byte[] url);
    Optional<List<Image>> findAllByPost(Post post);

}
