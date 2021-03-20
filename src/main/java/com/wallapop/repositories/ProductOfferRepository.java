package com.wallapop.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wallapop.entities.ProductOffer;
import com.wallapop.entities.User;

public interface ProductOfferRepository extends CrudRepository<ProductOffer, Long> {

	// TODO búsqueda de ofertas por texto (con paginación obligatoria)

	@Query("SELECT r FROM ProductOffer r WHERE (r.user = ?1) ORDER BY r.id ASC")
	List<ProductOffer> findOffersByUser(User user);

	@Query("SELECT r FROM ProductOffer r WHERE (r.user <> ?1) ORDER BY r.id ASC")
	Page<ProductOffer> searchNotBoughtOffers(Pageable pageable, User user);

	@Query("SELECT r FROM ProductOffer r WHERE (LOWER(r.title) LIKE LOWER(?1) AND r.user <> ?2) ORDER BY r.id ASC")
	Page<ProductOffer> searchByTitle(Pageable pageable, String textToSearch, User user);

}
