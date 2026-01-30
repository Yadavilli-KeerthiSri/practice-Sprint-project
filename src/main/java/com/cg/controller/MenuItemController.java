package com.cg.controller;

import com.cg.entity.MenuItem;
import com.cg.entity.Restaurant;
import com.cg.iservice.IMenuItem;
import com.cg.repository.RestaurantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/menu")
public class MenuItemController {

    private final IMenuItem menuItemService;
    private final RestaurantRepository restaurantRepo;

    public MenuItemController(IMenuItem menuItemService,
                              RestaurantRepository restaurantRepo) {
        this.menuItemService = menuItemService;
        this.restaurantRepo = restaurantRepo;
    }

    // LIST: show all restaurants and their menu items (Restaurant first, then items)
    @GetMapping
    public String showMenu(@RequestParam(value = "restaurantId", required = false) Integer restaurantId,
                           Model model) {

        List<Restaurant> restaurants = restaurantRepo.findAll();

        Map<Restaurant, List<MenuItem>> grouped = new LinkedHashMap<>();
        if (restaurantId != null) {
            // Only one restaurant selected
            Restaurant r = restaurantRepo.findById(restaurantId).orElse(null);
            if (r != null) {
                grouped.put(r, menuItemService.getByRestaurantId(restaurantId));
            }
        } else {
            // All restaurants with their items
            for (Restaurant r : restaurants) {
                grouped.put(r, menuItemService.getByRestaurantId(r.getRestaurantId()));
            }
        }

        model.addAttribute("grouped", grouped);
        model.addAttribute("restaurants", restaurants); // for dropdown in form/filter
        return "menu/index"; // templates/menu/index.html
    }

    // SHOW CREATE FORM
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        model.addAttribute("restaurants", restaurantRepo.findAll());
        return "menu/form"; // templates/menu/form.html
    }

    // CREATE
    @PostMapping
    public String create(@ModelAttribute("menuItem") MenuItem menuItem) {
        menuItemService.create(menuItem);
        return "redirect:/menu";
    }

    // SHOW EDIT FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Integer id, Model model) {
        MenuItem menuItem = menuItemService.getById(id).orElse(null);
        if (menuItem == null) return "redirect:/menu";
        model.addAttribute("menuItem", menuItem);
        model.addAttribute("restaurants", restaurantRepo.findAll());
        return "menu/form";
    }

    // UPDATE
    @PostMapping("/{id}")
    public String update(@PathVariable Integer id,
                         @ModelAttribute("menuItem") MenuItem menuItem) {
        menuItemService.update(id, menuItem);
        return "redirect:/menu";
    }

    // DELETE
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        menuItemService.delete(id);
        return "redirect:/menu";
    }
}