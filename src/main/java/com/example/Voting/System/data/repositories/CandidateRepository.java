package com.example.Voting.System.data.repositories;

import com.example.Voting.System.data.model.Candidate;
import com.example.Voting.System.data.model.Party;
import com.example.Voting.System.data.model.VoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Optional<Candidate> findByVoteCategoryAndParty(VoteCategory category, Party party);

    Optional<List<Candidate>> findCandidateByVoteCategory(VoteCategory category);
}
