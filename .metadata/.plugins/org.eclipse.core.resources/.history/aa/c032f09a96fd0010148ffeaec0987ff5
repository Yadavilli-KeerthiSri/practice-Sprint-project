package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Product;
import com.cg.repository.ProductRepository;
@Service
public class ProductService implements IProductService{
	@Autowired
	ProductRepository repo;
	
	@Override
	public List<Product> findAllProducts(){
		List<Product> productlist = new ArrayList<>();
//		productlist.add(new Product(1,"Washing Machine", 25000));
//		productlist.add(new Product(2,"TV", 50000));
//		productlist.add(new Product(3,"Fridge", 25000));
		productlist = repo.findAll();
		return productlist;
	}

	

	@Override
	public Optional<Product> findProductById(int id) {
		return repo.findById(id);
	}

	@Override
	public Product createProduct(Product product) {
		return repo.save(product);		
	}

	@Override
	public void deleteProduct(int id) {
		repo.deleteById(id);
	}
	
	public Product updateProduct(int id,Product p) {
		return repo.save(p);
		
	}



	public List<Product> getproductWithCategory() {
		return repo.getProductsWithCategory();
	}

//	 public ResponseEntity<Product> updateProduct(int id, Product changedProduct)
//	            throws ResourceNotFoundException {
//
//	        Product updatedProduct = repo.findById(id)
//	                .orElseThrow(() ->
//	                        new ResourceNotFoundException(
//	                                "Product not found with id: " + id));
//
//	        updatedProduct.setPname(changedProduct.getPname());
//	        updatedProduct.setPrice(changedProduct.getPrice());
//	        repo.save(updatedProduct);
// 
//	        return ResponseEntity.ok(repo.save(updatedProduct));
//	    }
//	 
//	 public List<Product> getproductWithCategory(){
//		 return repo.getProductsWithCategory();
//	 }

//	@Override
//	public Long countProductByName(String pname) {
//		return repo.countByProductName(pname);
//	} 
	
	
	

}
