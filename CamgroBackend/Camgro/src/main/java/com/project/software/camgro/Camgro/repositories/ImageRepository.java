package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Image;
import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, String> {

    Optional<Image> findTopByOrderByIdImageDesc();

    Optional<Image> findByUrl(byte[] url);
    Optional<List<Image>> findAllByPost(Post post);

    @Query("SELECT img FROM Image img WHERE CAST(SUBSTRING(img.idImage, 2)  AS INTEGER) = (SELECT MAX(CAST(SUBSTRING(img2.idImage, 2) AS int)) FROM Image  img2)")
    Optional<Image> findLastRecord();

}
