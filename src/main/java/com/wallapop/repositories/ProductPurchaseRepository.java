package com.wallapop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wallapop.entities.ProductPurchase;
import com.wallapop.entities.User;

public interface ProductPurchaseRepository extends CrudRepository<ProductPurchase,Long>{
	
	@Query("SELECT r FROM ProductPurchase r WHERE (r.offerBought.isSold = true and r.buyer = ?1) ORDER BY r.id ASC")
	List<ProductPurchase> findPurchasesByUser(User user);
}
