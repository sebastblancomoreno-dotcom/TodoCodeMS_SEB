package com.todocodeacademy.springsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.todocodeacademy.springsecurity.model.Role;
import com.todocodeacademy.springsecurity.repository.IRoleRepository;

public class RoleService implements IRoleService {

	@Autowired
	IRoleRepository roleRepository;

	@Override
	public List<Role> findAll() {

		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {

		return roleRepository.findById(id);
	}

	@Override
	public Role save(Role role) {

		return roleRepository.save(role);
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);

	}

	@Override
	public Role update(Role role) {

		return roleRepository.save(role);
	}

}
