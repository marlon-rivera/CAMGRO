package com.project.software.camgro.Camgro.services;

import com.project.software.camgro.Camgro.domain.Person;
import com.project.software.camgro.Camgro.domain.Post;
import com.project.software.camgro.Camgro.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public String getNewId(){
        Optional<Post> post = postRepository.findTopByOrderByIdPostDesc();
        int number;
        String id;
        if(post.isPresent()){
            id = post.get().getIdPost();
            number = Integer.parseInt(id.substring(2));
        }else {
            number = 0;
        }

        number++;
        id = "PO" + number;
        return id;
    }
}
