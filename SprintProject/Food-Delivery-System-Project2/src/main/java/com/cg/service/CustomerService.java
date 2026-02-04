package com.cg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Customer;
import com.cg.exception.ResourceNotFound;
import com.cg.iservice.ICustomerService;
import com.cg.repository.CustomerRepository;

@Service
public class CustomerService implements ICustomerService {
	@Autowired
    private CustomerRepository customerRepository;
 
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
 
    @Override
    public Customer updateCustomer(Integer id, Customer customer) throws ResourceNotFound {
        Customer existing = getCustomerById(id);
        existing.setCustomerName(customer.getCustomerName());
        existing.setContacts(customer.getContacts());
        existing.setEmail(customer.getEmail());
        existing.setAddress(customer.getAddress());
        return customerRepository.save(existing);
    }
 
    @Override
    public Customer getCustomerById(Integer id) throws ResourceNotFound {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Customer not found"));
    }
 
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
 
    @Override
    public void deleteCustomer(Integer id) throws ResourceNotFound {
        customerRepository.delete(getCustomerById(id));
    }
}