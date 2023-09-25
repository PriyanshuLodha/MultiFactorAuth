package com.example.MFA3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MFA3.model.UserSecretKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserSecretKeyRepo extends JpaRepository<UserSecretKey, Integer> {

	UserSecretKey findByUsername(String uname);

	@Transactional
	@Modifying
	@Query(value = "insert into secret_key(key, username) values (:key, :username)", nativeQuery = true)
	void saveKey(String key, String username);
}
