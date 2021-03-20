package com.wallapop.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wallapop.entities.User;
import com.wallapop.repositories.UserRepository;

@Service
public class UserService {

	// TODO añadir logger para diversos cambios en usuarios (detalles en pdf)

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ProductOfferService productOfferService;

	@PostConstruct
	public void init() {
	}

	// Paging no necesario en este caso
	public List<User> getUsers(User user) {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return userRepository.findById(id).get();
	}

	// Añadimos un nuevo usuario al repo, hay que añadir rol y saldo aquí para
	// evitar el error de GA
	// Cifrando como en el lab
	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		if (user.getEmail() != "admin@mail.com") {
			user.setRole("ROLE_CLIENT");
			user.setSaldo(100.0);
		}
		userRepository.save(user);
	}

	// Equivalente a buscar por DNI, es la otra clave aparte del id para identificar
	public User getUserByEmail(String mail) {
		return userRepository.findByEmail(mail);
	}

	// Tenemos que permitir el borrado simultáneo de varios usuarios
	public void deleteUser(String[] userIds) {
		for (String id : userIds) {
			User userToDelete = getUser(Long.parseLong(id));
			userRepository.delete(userToDelete);
		}

		// TODO incluir borrado de todas las ofertas asociadas al usuario
	}

	// Editar usuario no hace falta incluirlo en este caso
}
