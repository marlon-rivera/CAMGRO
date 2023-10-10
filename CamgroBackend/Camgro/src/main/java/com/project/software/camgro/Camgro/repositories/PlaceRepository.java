package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, String> {

    Optional<Place> findByTypeOfPlaceAndNamePlace(String typeOfPlace, String namePlace);

    Optional<Place> findByNamePlace(String namePlace);

    Optional<Place> findTopByOrderByIdPlaceDesc();
}
