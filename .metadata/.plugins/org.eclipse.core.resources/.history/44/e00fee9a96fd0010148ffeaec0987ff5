package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Category;
import com.cg.repository.CategoryRepository;
@Service
public class CategoryService {
	@Autowired
	CategoryRepository repo;
	
	public  List<Category> getAllCategory(){
		return repo.findAll();
		
	}
	
	public  Optional<Category> getCategoryById(int id){
		return repo.findById(id);
		 
	}

}
