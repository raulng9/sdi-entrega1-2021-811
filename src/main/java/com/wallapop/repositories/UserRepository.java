package com.wallapop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.wallapop.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	User findByEmail(String mail);
	
	Page<User> findAll(Pageable pageable); 

}
