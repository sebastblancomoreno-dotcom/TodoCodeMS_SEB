package com.todocodeacademy.springsecurity.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todocodeacademy.springsecurity.model.Permission;
import com.todocodeacademy.springsecurity.model.Role;
import com.todocodeacademy.springsecurity.service.IPermissionService;
import com.todocodeacademy.springsecurity.service.IRoleService;

@RestController
@RequestMapping("api/roles")
public class RolesController {
	
@Autowired
IRoleService rolesService;

@Autowired
IPermissionService permissionService;

@GetMapping 
public ResponseEntity <List<Role>> getAllRoles(){
	List<Role> roles =rolesService.findAll();
	return ResponseEntity.ok(roles);
	
}

@GetMapping("/{id}")
ResponseEntity<Role> getRoleById(@PathVariable Long id){
	Optional<Role> role = rolesService.findById(id);
	return role.map(ResponseEntity::ok).orElseGet(()->(ResponseEntity.notFound()).build());
	}
@PostMapping
public ResponseEntity<Role> createRole(@RequestBody Role role){//pasamos los permisos en el body de rol
	//creamos una lista de permisos que añadiremos al rol 
	Set<Permission> permiList = new HashSet<>();
	Permission readPermission;
	
	//Recuperar permisos por su id desde el role que pasamos en la solicitud
	for(Permission per: role.getPermissionList()) {
		readPermission = permissionService.findById(per.getId()).orElse(null);
		if(readPermission!=null) {
			permiList.add(readPermission);
		}
	}
	role.setPermissionList(permiList);
	Role newRole=rolesService.save(role);
	return ResponseEntity.ok(newRole);
	
}



}
