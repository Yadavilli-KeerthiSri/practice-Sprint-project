package com.cg.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.entity.MenuItem;
import com.cg.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
    List<MenuItem> findByRestaurant(Restaurant restaurant);

	List<MenuItem> findByItemNameContainingIgnoreCase(String query);

	List<MenuItem> findByRestaurantRestaurantId(Integer restaurantId);
}

