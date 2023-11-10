package com.example.Voting.System.data.repositories;

import com.example.Voting.System.data.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByBvn(String bvn);

    boolean existsByBvn(String bvn);
}
