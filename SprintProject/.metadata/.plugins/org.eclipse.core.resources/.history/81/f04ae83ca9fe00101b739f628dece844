package com.cg.service;

import com.cg.entity.Order;
import com.cg.entity.Payment;
import com.cg.exception.ResourceNotFound;
import com.cg.iservice.IPaymentService;
import com.cg.repository.OrderRepository;
import com.cg.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPaymentService {

	@Autowired
    private PaymentRepository paymentRepository;
 
    @Autowired
    private OrderRepository orderRepository;
 
    @Override
    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
 
    @Override
    public Payment getPaymentById(Integer id) throws ResourceNotFound {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Payment not found"));
    }
 
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
 
    @Override
    public Payment getPaymentByOrder(Integer orderId) throws ResourceNotFound {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFound("Order not found"));
        return order.getPayment();
    }
}