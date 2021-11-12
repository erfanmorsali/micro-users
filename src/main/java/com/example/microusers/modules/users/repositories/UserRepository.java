package com.example.microusers.modules.users.repositories;

import com.example.microusers.modules.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    User getUserById(Long id);
}
