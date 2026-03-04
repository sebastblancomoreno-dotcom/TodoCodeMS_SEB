package com.todocodeacademy.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todocodeacademy.springsecurity.model.UserSec;
import com.todocodeacademy.springsecurity.repository.IUserRepository;

@Service
public class UserDetailServiceImp implements UserDetailsService {

	@Autowired
	IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// UserSec -> UserDetail
		// Traer nuestro usuario de la BBDD
		UserSec userSec = userRepo.findUserEntityByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no fue encontrado"));

		// Creamos una lista para los permisos
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		
		// ESTO ES PARA TRAER Y CONVERTIR LOS PERMISOS A LOS TIPOS QUE SPRING SECURITY
		// NECESITA:
		// Traer permisos de los roles de usuario y convertirlos a
		// SimpleGrantedAuthority
		userSec.getRolesList().stream().flatMap(role -> role.getPermissionList().stream())
				.forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));

		// Traer roles y convertirlos a SimpleGrantedAuthority
		userSec.getRolesList()
				.forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

		return new User(userSec.getUsername(), userSec.getPassword(), userSec.isEnabled(),
				userSec.isAccountNotExpired(), userSec.isAccountNotLocked(), userSec.isCredentialNotExpired(),
				authorityList);

	}

}
