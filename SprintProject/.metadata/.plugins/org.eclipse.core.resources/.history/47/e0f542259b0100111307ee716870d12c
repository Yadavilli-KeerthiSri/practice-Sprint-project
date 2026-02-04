package com.cg.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.repository.MenuItemRepository;
import com.cg.repository.RestaurantRepository;

@Service
public class SearchService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Map<String, Object> search(String keyword) {

        Map<String, Object> result = new HashMap<>();
        result.put("restaurants",
                restaurantRepository.findByRestaurantNameContainingIgnoreCase(keyword));
        result.put("menuItems",
                menuItemRepository.findByItemNameContainingIgnoreCase(keyword));

        return result;
    }
}

