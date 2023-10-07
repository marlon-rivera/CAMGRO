package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
