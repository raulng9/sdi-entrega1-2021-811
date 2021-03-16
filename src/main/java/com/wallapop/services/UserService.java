package com.wallapop.services;

import java.util.LinkedList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wallapop.entities.User;
import com.wallapop.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@PostConstruct
	public void init() {
	}

	// Versión con paging directamente del lab
	public Page<User> getUsers(Pageable pageable, User user) {
		Page<User> users = new PageImpl<User>(new LinkedList<User>());
		if (user.getRole().equals("ROLE_ADMIN")) {
			users = userRepository.findAll(pageable);
		}
		return users;
	}

	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	// Añadimos un nuevo usuario al repo, con crédito estándar 100 y rol de cliente
	// normal
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public User getUserByEmail(String mail) {
		return userRepository.findByEmail(mail);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	//Editar usuario en principio no
}
