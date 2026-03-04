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

import com.todocodeacademy.springsecurity.model.Role;
import com.todocodeacademy.springsecurity.model.UserSec;
import com.todocodeacademy.springsecurity.service.IRoleService;
import com.todocodeacademy.springsecurity.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@GetMapping
	public ResponseEntity<List<UserSec>> getAllUsers() {
		List<UserSec> users = userService.findAll();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserSec> getUSerByid(@PathVariable Long id) {
		Optional<UserSec> user = userService.findById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<UserSec> createUser(@RequestBody UserSec userSec) {
		Set<Role> rolesList = new HashSet<Role>();

		// Lo creamos para guardar la lectura al recorrer la lista que pasamos por el
		// body de l arequest
		Role readRoles;

		for (Role userRol : userSec.getRolesList()) {
			readRoles = roleService.findById(userRol.getId()).orElse(null);
			if (readRoles != null) {
				rolesList.add(readRoles);
			}
		}
		// añadimos la lista a user y guardamos en BBDD
		if (!rolesList.isEmpty()) {
			userSec.setRolesList(rolesList);
			UserSec newUser = userService.save(userSec);
			return ResponseEntity.ok(newUser);
		}

		return null;
	}
}
