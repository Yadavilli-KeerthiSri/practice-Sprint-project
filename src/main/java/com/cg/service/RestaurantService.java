package com.cg.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.entity.Restaurant;
import com.cg.iservice.IRestaurant;
import com.cg.repository.RestaurantRepository;


@Service
public class RestaurantService implements IRestaurant {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Integer id, Restaurant restaurant) {
        Restaurant existing = restaurantRepository.findById(id).orElse(null);

        if(existing != null) {
            existing.setRestaurantName(restaurant.getRestaurantName());
            existing.setAddress(restaurant.getAddress());
            existing.setCuisine(restaurant.getCuisine());
            existing.setRatings(restaurant.getRatings());
            return restaurantRepository.save(existing);
        }

        return null;
    }

    @Override
    public void delete(Integer id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public Optional<Restaurant> getById(Integer id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchByName(String name) {
        return restaurantRepository.findByRestaurantNameContainingIgnoreCase(name);
    }

    @Override
    public List<Restaurant> getByCuisine(String cuisine) {
        return restaurantRepository.findByCuisineIgnoreCase(cuisine);
    }
	    @Override
	    public List<String> getAllCuisines() {
	        return restaurantRepository.findDistinctCuisines();
	    }

	    @Override
	    public List<Restaurant> filter(String name, String cuisine) {
	        boolean hasName = name != null && !name.isBlank();
	        boolean hasCuisine = cuisine != null && !cuisine.isBlank();

	        if (hasName && hasCuisine) {
	            return restaurantRepository
	                    .findByRestaurantNameContainingIgnoreCaseAndCuisineIgnoreCase(name.trim(), cuisine.trim());
	        } else if (hasName) {
	            return restaurantRepository.findByRestaurantNameContainingIgnoreCase(name.trim());
	        } else if (hasCuisine) {
	            return restaurantRepository.findByCuisineIgnoreCase(cuisine.trim());
	        } else {
	            return restaurantRepository.findAll();
	        }
	    }
	}