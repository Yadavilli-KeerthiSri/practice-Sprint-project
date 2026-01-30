package com.cg.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Category;
import com.cg.service.CategoryService;
 
@Controller
@RequestMapping("/capi")   //http://localhost:8080/capi/clist
public class CategoryController {
@Autowired
CategoryService categoryservice;
 
@GetMapping("/clist")   
public String  findAllCategories(Model model) {
	model.addAttribute("categories",categoryservice.getAllCategory());
	return "category/list-category";
}
@GetMapping("/category/{id}/products")
public String listProductsByCategory(@PathVariable int id, Model model) throws ResourceNotFoundException {
    // 1. Find the category by its ID 
	Category category = categoryservice.getCategoryById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
 
    // 2. Add the list of products associated with this category to the model
    // This works because of the @OneToMany mapping in Category entity
    model.addAttribute("products", category.getProducts());
    model.addAttribute("categoryName", category.getCname());
 
    // 3. Return the product list view (the same one used for /api/list)
    return "product/list-product"; 
}
 
}