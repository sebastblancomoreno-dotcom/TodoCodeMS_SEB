package com.todocodeacademy.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todocodeacademy.springsecurity.model.Role;
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}
