package de.myproject.demo;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

public interface UserAccountRepository extends ListCrudRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);
    
}
