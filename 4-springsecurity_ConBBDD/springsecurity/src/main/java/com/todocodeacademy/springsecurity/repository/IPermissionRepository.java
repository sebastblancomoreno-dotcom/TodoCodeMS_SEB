package com.todocodeacademy.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todocodeacademy.springsecurity.model.Permission;
@Repository
public interface IPermissionRepository extends JpaRepository<Permission, Long>{

}
