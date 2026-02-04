package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.entity.Restaurant;
import com.cg.exception.ResourceNotFound;
import com.cg.service.RestaurantService;

@Controller
@RequestMapping("/restaurants")
public class RestaurantController {
	@Autowired
    private RestaurantService restaurantService;
 
    @GetMapping
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "restaurants";
    }
 
    @PostMapping("/add")
    public String addRestaurant(Restaurant restaurant) {
        restaurantService.createRestaurant(restaurant);
        return "redirect:/restaurants";
    }
 
    @PostMapping("/update/{id}")
    public String updateRestaurant(@PathVariable Integer id, Restaurant restaurant) throws ResourceNotFound {
        restaurantService.updateRestaurant(id, restaurant);
        return "redirect:/restaurants";
    }
 
    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable Integer id) throws ResourceNotFound {
        restaurantService.deleteRestaurant(id);
        return "redirect:/restaurants";
    }
}
