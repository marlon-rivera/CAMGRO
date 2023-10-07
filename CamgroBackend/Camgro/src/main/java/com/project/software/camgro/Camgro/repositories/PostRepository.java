package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}
