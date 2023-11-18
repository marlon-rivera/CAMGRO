package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {

    Optional<Post> findTopByOrderByIdPostDesc();

    @Query("SELECT pu FROM publicaciones pu WHERE CAST(SUBSTRING(pu.idPost, 3)  AS INTEGER) = (SELECT MAX(CAST(SUBSTRING(pu2.idPost, 3) AS int)) FROM publicaciones pu2)")
    Optional<Post> findLastRecord();
}
