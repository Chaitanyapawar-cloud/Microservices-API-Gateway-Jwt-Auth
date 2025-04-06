package com.csp.api.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csp.api.entity.UserCredentials;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {

	Optional<UserCredentials> findByUserName(String username);

}
