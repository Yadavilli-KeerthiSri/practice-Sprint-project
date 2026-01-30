package com.cg.repository;

import com.cg.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> findByRestaurantNameContainingIgnoreCase(String name);

    List<Restaurant> findByCuisineIgnoreCase(String cuisine);

    // Combined: name + cuisine (both optional – we’ll control in service)
    List<Restaurant> findByRestaurantNameContainingIgnoreCaseAndCuisineIgnoreCase(String name, String cuisine);

    // Distinct list for dropdown
    @Query("select distinct r.cuisine from Restaurant r where r.cuisine is not null order by r.cuisine")
    List<String> findDistinctCuisines();
}