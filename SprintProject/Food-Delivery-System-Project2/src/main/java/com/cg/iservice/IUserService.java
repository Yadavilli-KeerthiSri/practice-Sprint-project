package com.cg.iservice;

import java.util.List;

import com.cg.entity.User;
import com.cg.exception.ResourceNotFound;

public interface IUserService {
	User registerUser(User user);
	 
    User findByUsername(String username) throws ResourceNotFound;
 
    List<User> getAllUsers();
}
