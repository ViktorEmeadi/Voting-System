package com.example.Voting.System.data.repositories;

import com.example.Voting.System.data.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
