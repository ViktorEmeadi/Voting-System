package com.example.Voting.System.data.repositories;

import com.example.Voting.System.data.model.Admin;
import com.example.Voting.System.data.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    Optional<Voter> findByEmailAndBvn(String email, String bvn);

    Optional<Voter> findVoterByPasswordAndEmail(String password, String email);

    Optional<Voter> findByBvn(String bvn);
}
