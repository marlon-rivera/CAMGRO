package com.project.software.camgro.Camgro.controllers;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.ErrorMesage;
import com.project.software.camgro.Camgro.domain.Post;
import com.project.software.camgro.Camgro.domain.PostAccounts;
import com.project.software.camgro.Camgro.records.AccountResponse;
import com.project.software.camgro.Camgro.repositories.AccountRepository;
import com.project.software.camgro.Camgro.repositories.PersonRepository;
import com.project.software.camgro.Camgro.repositories.PostAccountsRepository;
import com.project.software.camgro.Camgro.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AccountRepository accountRepository;
    private final PostRepository postRepository;
    private final PostAccountsRepository postAccountsRepository;

    @GetMapping(value = "all-accounts")
    public ResponseEntity<?> getAllAccounts(@RequestHeader HttpHeaders headers){
        System.out.println("Entre");
        if(headers.containsKey("Role") && headers.getFirst("Role").equals("ADMIN")){
            List<Account> accountList = accountRepository.findAll();
            List<AccountResponse> result = new ArrayList<>();
            for (Account account :
                    accountList) {
                if(account.isActive()){
                    result.add(new AccountResponse(account.getId().substring(2), account.getEmail(), account.getRole(), account.getPerson()));
                }

            }
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("No tiene los permisos requeridos."));
    }

    @DeleteMapping(value = "delete-account/{idAccount}")
    public ResponseEntity<?> deleteAccount(@RequestHeader HttpHeaders headers, @PathVariable("idAccount") String idAccount){
         if(headers.containsKey("Role") && headers.getFirst("Role").equals("ADMIN")){
            Optional<Account> account = accountRepository.findById("AC" + idAccount);
            if(account.isPresent()){
                account.get().setActive(false);
                accountRepository.save(account.get());
                List<PostAccounts> postsAccounts = postAccountsRepository.findAllByPostsAccountsPrimaryKeyAccountAndOwnTrue(account.get());

                    for (PostAccounts postAccounts :
                            postsAccounts) {
                        Post post = postAccounts.getPostsAccountsPrimaryKey().getPost();
                        post.setActive(false);
                        postRepository.save(post);
                    }

                return ResponseEntity.ok(new ErrorMesage("Se borro la cuenta."));
            }
            return ResponseEntity.badRequest().body(new ErrorMesage("No se encontró la cuenta deseada."));
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("No tiene los permisos requeridos."));
    }

    @DeleteMapping(value = "delete-post/{idPost}")
    public ResponseEntity<?> deletePost(@RequestHeader HttpHeaders headers, @PathVariable("idPost") String idPost){
        if(headers.containsKey("Role") && headers.getFirst("Role").equals("ADMIN")){
            Optional<Post> postOp = postRepository.findById("PO" + idPost);
            if(postOp.isPresent()){
                postOp.get().setActive(false);
                postRepository.save(postOp.get());
                return ResponseEntity.ok(new ErrorMesage("Se borro la publicación."));
            }
            return ResponseEntity.badRequest().body(new ErrorMesage("No se encontró la publicación deseada."));
        }
        return ResponseEntity.badRequest().body(new ErrorMesage("No tiene los permisos requeridos."));
    }

}
