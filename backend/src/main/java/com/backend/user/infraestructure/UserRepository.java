package com.backend.user.infraestructure;

import com.backend.user.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>  {
    Optional<User> findByUsername(String username);
}
