package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.MenuItem;
import com.cg.entity.Restaurant;
import com.cg.exception.ResourceNotFound;
import com.cg.iservice.IMenuItemService;
import com.cg.repository.MenuItemRepository;
import com.cg.repository.RestaurantRepository;

@Service
public class MenuItemService implements IMenuItemService{
	@Autowired
    private MenuItemRepository menuItemRepository;
 
    @Autowired
    private RestaurantRepository restaurantRepository;
 
    @Override
    public MenuItem createMenuItem(MenuItem item) {
        return menuItemRepository.save(item);
    }
 
    @Override
    public MenuItem updateMenuItem(Integer id, MenuItem item) throws ResourceNotFound {
        MenuItem existing = getMenuItemById(id);
        existing.setItemName(item.getItemName());
        existing.setPrice(item.getPrice());
        existing.setCategory(item.getCategory());
        existing.setRestaurant(item.getRestaurant());
        return menuItemRepository.save(existing);
    }
 
    @Override
    public MenuItem getMenuItemById(Integer id) throws ResourceNotFound {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Menu item not found"));
    }
 
    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }
 
    @Override
    public List<MenuItem> getMenuItemsByRestaurant(Integer restaurantId) throws ResourceNotFound {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFound("Restaurant not found"));
        return restaurant.getMenuItems();
    }
 
    @Override
    public void deleteMenuItem(Integer id) throws ResourceNotFound {
        menuItemRepository.delete(getMenuItemById(id));
    }
}
