package com.todocodeacademy.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todocodeacademy.springsecurity.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {

}
