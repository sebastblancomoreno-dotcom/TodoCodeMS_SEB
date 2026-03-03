package com.todocodeacademy.springsecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todocodeacademy.springsecurity.model.UserSec;

public interface IUserRepository extends JpaRepository<UserSec, Long> {
	
	Optional<UserSec> findUserEntityByUsername(String username);
}
