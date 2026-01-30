package com.cg.dto;
public class MenuItemResponse {

    // put these first to appear at the top in JSON
    private Integer restaurantId;
    private String restaurantName;

    // then menu item fields
    private Integer itemId;
    private String itemName;
    private Float price;
    private String category;

    // getters/setters
    public Integer getRestaurantId() 
    { 
    	return restaurantId;
       }
    public void setRestaurantId(Integer restaurantId) {
    	this.restaurantId = restaurantId; 
    	}

    public String getRestaurantName() { 
    	return restaurantName;
    	}
    public void setRestaurantName(String restaurantName) { 
    	this.restaurantName = restaurantName; 
    	}

    public Integer getItemId() {
    	return itemId;
    	}
    public void setItemId(Integer itemId) { 
    	this.itemId = itemId; 
    	}

    public String getItemName() { 
    	return itemName; 
    	}
    public void setItemName(String itemName) {
    	this.itemName = itemName; 
    	}

    public Float getPrice() { 
    	return price; 
    	}
    public void setPrice(Float price) { 
    	this.price = price;
    	}

    public String getCategory() { 
    	return category; 
    	}
    public void setCategory(String category) { 
    	this.category = category; 
    	}
}
