package com.example.MFA3.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MFA3.model.UserSecretKey;

public interface UserSecretKeyRepo extends JpaRepository<UserSecretKey, Integer> {

	Optional<UserSecretKey> findByUsername(String uname);
}
