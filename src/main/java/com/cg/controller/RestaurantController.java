package com.cg.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cg.iservice.IRestaurant;
import com.cg.iservice.IRestaurant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private final IRestaurant restaurantService;

    public RestaurantController(IRestaurant restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String showRestaurants(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "cuisine", required = false) String cuisine,
            Model model) {

        // 1) Load filtered restaurants
        model.addAttribute("restaurants", restaurantService.filter(name, cuisine));

        // 2) All cuisines for dropdown
        model.addAttribute("cuisines", restaurantService.getAllCuisines());

        // 3) Preserve user selections in UI
        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("selectedCuisine", cuisine == null ? "" : cuisine);

        // 4) Load Thymeleaf page: templates/restaurants/index.html
        return "restaurants/index";
    }
}