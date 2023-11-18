package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, String> {

    @Query("SELECT p FROM Person p WHERE CAST(SUBSTRING(p.id, 2)  AS INTEGER) = (SELECT MAX(CAST(SUBSTRING(p2.id, 2) AS int)) FROM Person p2)")
    Optional<Person> findLastRecord();

    List<Person> findPersonByAddress(String address);
}