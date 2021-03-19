package com.wallapop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wallapop.entities.ProductOffer;
import com.wallapop.entities.User;

public interface ProductOfferRepository extends CrudRepository<ProductOffer,Long>{
	
	//TODO búsqueda de ofertas por texto (con paginación obligatoria)
	
	@Query("SELECT r FROM ProductOffer r WHERE (r.user = ?1) ORDER BY r.id ASC")
	List<ProductOffer> findOffersByUser(User user);

}