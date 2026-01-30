package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Product;

public interface IProductService {
	public List<Product> findAllProducts();
	public Optional<Product> findProductById(int id);
	public Product createProduct(Product product);
	public void deleteProduct(int id);
	public Product updateProduct(int id,Product p);
//	public ResponseEntity<Product> updateProduct(int productId, Product changedProduct)throws  ResourceNotFoundException;
//	public Long countProductByName(String pname);
}
