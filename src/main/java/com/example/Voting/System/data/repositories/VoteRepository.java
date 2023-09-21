package com.example.Voting.System.data.repositories;

import com.example.Voting.System.data.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
