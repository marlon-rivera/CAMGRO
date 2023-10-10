package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {
    Optional<Person> findTopByOrderByIdDesc();
}