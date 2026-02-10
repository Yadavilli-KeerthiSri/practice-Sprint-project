package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.RestaurantDto;
import com.cg.entity.Restaurant;
import com.cg.iservice.IRestaurantService;
import com.cg.mapper.RestaurantMapper;
import com.cg.repository.MenuItemRepository;
import com.cg.repository.RestaurantRepository;

@Service
public class RestaurantService implements IRestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    public RestaurantDto add(RestaurantDto dto) {
        Restaurant entity = RestaurantMapper.fromCreateDto(
                dto,
                ids -> menuItemRepository.findAllById(ids)
        );
        Restaurant saved = restaurantRepository.save(entity);
        return RestaurantMapper.toDto(saved);
    }

    @Override
    public RestaurantDto update(RestaurantDto dto) {
        Restaurant existing = restaurantRepository.findById(dto.getRestaurantId()).orElseThrow();
        RestaurantMapper.applyUpdate(
                dto,
                existing,
                ids -> menuItemRepository.findAllById(ids)
        );
        Restaurant saved = restaurantRepository.save(existing);
        return RestaurantMapper.toDto(saved);
    }

    @Override
    public RestaurantDto getById(Long id) {
        return restaurantRepository.findById(id)
                .map(RestaurantMapper::toDto)
                .orElseThrow();
    }

    @Override
    public List<RestaurantDto> getAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantDto> findTopRated() {
        // Your original method used 'findTop6ByRatingsGreaterThanEqualOrderByRatingsDesc'
        List<Restaurant> top = restaurantRepository
                .findTop6ByRatingsGreaterThanEqualOrderByRatingsDesc(4.0);
        return top.stream().map(RestaurantMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        restaurantRepository.deleteById(id);
    }
}
