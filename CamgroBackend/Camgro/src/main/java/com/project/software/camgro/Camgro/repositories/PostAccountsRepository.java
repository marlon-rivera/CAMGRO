package com.project.software.camgro.Camgro.repositories;

import com.project.software.camgro.Camgro.domain.Account;
import com.project.software.camgro.Camgro.domain.Post;
import com.project.software.camgro.Camgro.domain.PostAccounts;
import com.project.software.camgro.Camgro.domain.PostsAccountsPrimaryKey;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostAccountsRepository extends JpaRepository<PostAccounts, PostsAccountsPrimaryKey> {

    List<PostAccounts> findAllByPostsAccountsPrimaryKeyAccountAndOwnTrue(Account account);

    List<PostAccounts> findAllByPostsAccountsPrimaryKeyAccountAndOwnFalse(Account account);

    Optional<PostAccounts> findByPostsAccountsPrimaryKeyPostAndOwnTrue(Post post);
}
