package com.cg.iservice;

import java.util.List;
import java.util.Optional;
import com.cg.entity.Restaurant;

public interface IRestaurant {

    Restaurant create(Restaurant restaurant);

    Restaurant update(Integer id, Restaurant restaurant);

    void delete(Integer id);

    Optional<Restaurant> getById(Integer id);

    List<Restaurant> getAll();

    List<Restaurant> searchByName(String name);

    List<Restaurant> getByCuisine(String cuisine);
    List<String> getAllCuisines();
    List<Restaurant> filter(String name, String cuisine);
}

