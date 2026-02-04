package com.cg.controller.user;

import com.cg.repository.MenuItemRepository;
import com.cg.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserSearchController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @GetMapping("/user/search")
    public String search(@RequestParam String keyword, Model model) {

        model.addAttribute("restaurants",
                restaurantRepository.findByRestaurantNameContainingIgnoreCase(keyword));

        model.addAttribute("menuItems",
                menuItemRepository.findByItemNameContainingIgnoreCase(keyword));

        return "user/search-results";
    }
}

