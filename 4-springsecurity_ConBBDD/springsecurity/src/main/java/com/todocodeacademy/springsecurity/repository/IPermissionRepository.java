package com.todocodeacademy.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todocodeacademy.springsecurity.model.Permission;

public interface IPermissionRepository extends JpaRepository<Permission, Long>{

}
