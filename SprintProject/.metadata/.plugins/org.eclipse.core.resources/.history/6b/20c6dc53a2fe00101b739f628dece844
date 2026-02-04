package com.cg.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.Order;
import com.cg.entity.OrderStatus;
import com.cg.exception.ResourceNotFound;
import com.cg.iservice.IOrderService;
import com.cg.repository.CustomerRepository;
import com.cg.repository.OrderRepository;

@Service
public class OrderService implements IOrderService{
	@Autowired
    private OrderRepository orderRepository;
 
    @Autowired
    private CustomerRepository customerRepository;
 
    @Override
    public Order placeOrder(Order order) {
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.PLACED);
        return orderRepository.save(order);
    }
 
    @Override
    public Order updateOrderStatus(Integer orderId, OrderStatus status) throws ResourceNotFound {
        Order order = getOrderById(orderId);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
 
    @Override
    public Order getOrderById(Integer orderId) throws ResourceNotFound {
        return (Order) orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFound("Order not found"));
    }
 
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
 
    @Override
    public List<Order> getOrdersByCustomer(Integer customerId) throws ResourceNotFound {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFound("Customer not found"))
                .getOrders();
    }
 
    @Override
    public void cancelOrder(Integer orderId) throws ResourceNotFound {
        Order order = getOrderById(orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }
}
