package com.project.software.camgro.Camgro.unitTests;

import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Place;
import com.project.software.camgro.Camgro.domain.Post;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PlaceRepository;
import com.project.software.camgro.Camgro.repositories.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @Test
    public void PostRepository_Save_SavePost(){
        Post post = Post.builder().idPost("PO100").build();
        Post postSaved = postRepository.save(post);
        Assertions.assertThat(postSaved.getIdPost()).isEqualTo("PO100");
    }

    @Test
    public void PostRepository_Modify_ModifyPost(){

        Optional<Post> postOp = postRepository.findById("PO1");
        Post postModified = postOp.get();
        postModified.setPostTitle("TESTING");
        postRepository.save(postModified);

        Post modified = postRepository.findById("PO1").get();

        Assertions.assertThat(modified.getPostTitle()).isEqualTo("TESTING");
    }

    @Test
    public void PostRepository_Delete_DeletePost(){
        postRepository.deleteById("PO1");
        Optional<Post> postDeleted = postRepository.findById("PO1");
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchElementException.class, () -> postDeleted.get());
    }

    @Test
    public void PostRepository_Find_FindByPlace(){

        Place place = Place.builder().idPlace("PL101").typeOfPlace("C").lugIdLug(null).namePlace("TESTING").build();

        placeRepository.save(place);

        Post post1 = Post.builder().idPost("PO101").measureUnit("TESTING").priceProduct(50).postStatus("TESTING").postTitle("TESTING").descriptionPost("TESTING").harvestDate(LocalDate.now()).publicationDate(LocalDate.now()).place(place).build();
        Post post2 = Post.builder().idPost("PO102").measureUnit("TESTING").priceProduct(50).postStatus("TESTING").postTitle("TESTING").descriptionPost("TESTING").harvestDate(LocalDate.now()).publicationDate(LocalDate.now()).place(place).build();
        Post post3 = Post.builder().idPost("PO103").measureUnit("TESTING").priceProduct(50).postStatus("TESTING").postTitle("TESTING").descriptionPost("TESTING").harvestDate(LocalDate.now()).publicationDate(LocalDate.now()).place(place).build();

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        List<Post> postList = postRepository.findAllByPlace(place);

        Assertions.assertThat(postList.size()).isEqualTo(3);
    }
}
