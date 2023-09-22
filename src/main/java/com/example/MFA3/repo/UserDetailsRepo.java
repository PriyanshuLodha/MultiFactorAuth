package com.example.MFA3.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MFA3.model.User;

public interface UserDetailsRepo extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String uname);
}
