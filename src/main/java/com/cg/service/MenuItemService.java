package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.entity.MenuItem;
import com.cg.entity.Restaurant;
import com.cg.iservice.IMenuItem;
import com.cg.repository.MenuItemRepository;
import com.cg.repository.RestaurantRepository;

@Service
public class MenuItemService implements IMenuItem {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository,
                           RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public MenuItem create(MenuItem menuItem) {

        // IMPORTANT FIX
        if (menuItem.getRestaurant() != null &&
            menuItem.getRestaurant().getRestaurantId() != null) {

            Restaurant r = restaurantRepository
                    .findById(menuItem.getRestaurant().getRestaurantId())
                    .orElse(null);

            menuItem.setRestaurant(r);
        }

        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem update(Integer id, MenuItem menuItem) {

        MenuItem existing = menuItemRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setItemName(menuItem.getItemName());
            existing.setPrice(menuItem.getPrice());
            existing.setCategory(menuItem.getCategory());

            // VERY IMPORTANT PART FOR EDIT
            if (menuItem.getRestaurant() != null &&
                    menuItem.getRestaurant().getRestaurantId() != null) {

                Restaurant r = restaurantRepository
                        .findById(menuItem.getRestaurant().getRestaurantId())
                        .orElse(null);

                existing.setRestaurant(r);
            }

            return menuItemRepository.save(existing);
        }

        return null;
    }

    @Override
    public void delete(Integer id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public Optional<MenuItem> getById(Integer id) {
        return menuItemRepository.findById(id);
    }

    @Override
    public List<MenuItem> getAll() {
        return menuItemRepository.findAll();
    }

    @Override
    public List<MenuItem> getByRestaurantId(Integer restaurantId) {
        return menuItemRepository.findByRestaurantRestaurantId(restaurantId);
    }

    @Override
    public List<MenuItem> searchByName(String query) {
        return menuItemRepository.findByItemNameContainingIgnoreCase(query);
    }
}
