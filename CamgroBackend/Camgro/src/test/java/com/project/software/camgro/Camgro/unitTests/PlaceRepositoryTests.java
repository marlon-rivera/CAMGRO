package com.project.software.camgro.Camgro.unitTests;

import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlaceRepositoryTests {

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    public void PlaceRepository_Save_ReturnPlace(){
        Place place = Place.builder().namePlace("TESTING").idPlace("PL100").typeOfPlace("D").lugIdLug(null).build();
        Place placeSaved = placeRepository.save(place);
        Assertions.assertThat(placeSaved.getIdPlace()).isEqualTo("PL100");
    }

    @Test
    public void PlaceRepository_Modify_ModifyPlace(){
        Optional<Place> placeOp = placeRepository.findById("PL1");
        Place placeModified = placeOp.get();
        placeModified.setNamePlace("TESTING");
        Place modified = placeRepository.save(placeModified);
        Assertions.assertThat(modified.getNamePlace()).isEqualTo("TESTING");
    }

    @Test
    public void PlaceRepository_Delete_DeletePlace(){
       placeRepository.deleteById("PL1");
       Optional<Place> placeOp = placeRepository.findById("PL1");
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> placeOp.get());
    }

    @Test
    public void PlaceRepository_Find_FindAllByLugIdLug(){
        Place place = Place.builder().namePlace("TESTING").idPlace("PL100").typeOfPlace("D").lugIdLug(null).build();
        Place place1 = Place.builder().namePlace("TESTING1").idPlace("PL101").typeOfPlace("C").lugIdLug(place).build();
        Place place2 = Place.builder().namePlace("TESTING2").idPlace("PL102").typeOfPlace("C").lugIdLug(place).build();
        Place place3 = Place.builder().namePlace("TESTING3").idPlace("PL103").typeOfPlace("C").lugIdLug(place).build();

        placeRepository.save(place);
        placeRepository.save(place1);
        placeRepository.save(place2);
        placeRepository.save(place3);

        List<Place> placeList = placeRepository.findAllByLugIdLug(place);

        Assertions.assertThat(placeList.size()).isEqualTo(3);
    }
}
