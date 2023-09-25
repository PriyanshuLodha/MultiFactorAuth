package com.example.MFA3.repo;

import com.example.MFA3.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestRepo extends JpaRepository<Test, Integer> {
    Optional<Test> findByUsername(String name);
}
