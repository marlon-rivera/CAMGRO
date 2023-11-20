package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, String> {

    Optional<Place> findByTypeOfPlaceAndNamePlace(String typeOfPlace, String namePlace);

    Optional<Place> findByNamePlace(String namePlace);

    Optional<Place> findTopByOrderByIdPlaceDesc();

    @Query("SELECT pl FROM Place pl WHERE CAST(SUBSTRING(pl.idPlace, 3)  AS INTEGER) = (SELECT MAX(CAST(SUBSTRING(pl2.idPlace, 3) AS int)) FROM Place pl2)")
    Optional<Place> findLastRecord();

    List<Place> findAllByLugIdLug(Place place);
}
