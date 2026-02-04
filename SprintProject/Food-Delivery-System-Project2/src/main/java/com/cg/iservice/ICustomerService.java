package com.cg.iservice;

import com.cg.entity.Customer;
import com.cg.exception.ResourceNotFound;

import java.util.List;

public interface ICustomerService {
	Customer createCustomer(Customer customer);
	 
    Customer updateCustomer(Integer id, Customer customer) throws ResourceNotFound;
 
    Customer getCustomerById(Integer id) throws ResourceNotFound;
 
    List<Customer> getAllCustomers();
 
    void deleteCustomer(Integer id) throws ResourceNotFound;
}

