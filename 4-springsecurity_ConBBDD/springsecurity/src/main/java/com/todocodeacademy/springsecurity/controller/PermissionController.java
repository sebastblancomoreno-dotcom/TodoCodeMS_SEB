package com.todocodeacademy.springsecurity.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todocodeacademy.springsecurity.model.Permission;
import com.todocodeacademy.springsecurity.service.IPermissionService;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
	@Autowired
	private IPermissionService permissionService;

	@GetMapping
	public ResponseEntity<List<Permission>> getAllPermissions() {
		List<Permission> permissions= permissionService.findAll();
		return ResponseEntity.ok(permissions);
		
	}

}
