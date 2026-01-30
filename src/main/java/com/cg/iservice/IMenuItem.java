package com.cg.iservice;

import java.util.List;
import java.util.Optional;
import com.cg.entity.MenuItem;

public interface IMenuItem {

    MenuItem create(MenuItem menuItem);

    MenuItem update(Integer id, MenuItem menuItem);

    void delete(Integer id);

    Optional<MenuItem> getById(Integer id);

    List<MenuItem> getAll();

    List<MenuItem> getByRestaurantId(Integer restaurantId);

    List<MenuItem> searchByName(String query);
}