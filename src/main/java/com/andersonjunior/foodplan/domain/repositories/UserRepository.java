package com.andersonjunior.foodplan.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonjunior.foodplan.domain.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);
    List<User> findByNameContainsIgnoreCase(String name);
    
}
