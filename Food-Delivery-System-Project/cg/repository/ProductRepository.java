package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("select count(p) from Product p where p.pname= :pname")
	Long countByProductName(String pname);
	 @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category")
	    List<Product> getProductsWithCategory();

}
