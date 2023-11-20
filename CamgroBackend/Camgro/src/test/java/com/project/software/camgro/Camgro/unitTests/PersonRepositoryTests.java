package com.project.software.camgro.Camgro.unitTests;

import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
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
public class PersonRepositoryTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void PersonRepository_Save_ReturnSavePerson(){
        String id = "P100";
        Person person = Person.builder().id(id).name("TESTING").phone("1111111111").address("Address").build();
         Person personSave = personRepository.save(person);
        Assertions.assertThat(personSave).isNotNull();
        Assertions.assertThat(personSave.getId()).isEqualTo(id);
    }

    @Test
    public void PersonRepostiroy_Delete_ReturnDeletePerson(){
        String id = "P1";
        personRepository.deleteById(id);
        Optional<Person> personDelete = personRepository.findById(id);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> personDelete.get());
    }

    @Test
    public void PersonRepository_Modify_ReturnModifyPerson() {
        String id = "P1";
        Optional<Person> personModifiedOp = personRepository.findById(id);
        Person personModify = personModifiedOp.get();
        personModify.setName("TESTING");
        Person modified = personRepository.save(personModify);
        Assertions.assertThat(modified.getName()).isEqualTo("TESTING");
    }

    @Test
    public void PersonRepository_Find_FindByPlacePersons(){
        Person person = Person.builder().id("P100").name("TESTING").phone("1111111111").address("Address").build();
        Person person1 = Person.builder().id("P101").name("TESTING").phone("1111111111").address("Address").build();
        Person person2 = Person.builder().id("P102").name("TESTING").phone("1111111111").address("Address").build();
        personRepository.save(person);
        personRepository.save(person1);
        personRepository.save(person2);
        List<Person> personListAddress = personRepository.findPersonByAddress("Address");

        Assertions.assertThat(personListAddress.size()).isEqualTo(3);
    }
}
