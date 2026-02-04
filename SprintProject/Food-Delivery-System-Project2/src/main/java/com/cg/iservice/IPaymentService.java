package com.cg.iservice;

import java.util.List;

import com.cg.entity.Payment;
import com.cg.exception.ResourceNotFound;

public interface IPaymentService {
	Payment makePayment(Payment payment);
	 
    Payment getPaymentById(Integer id) throws ResourceNotFound;
 
    List<Payment> getAllPayments();
 
    Payment getPaymentByOrder(Integer orderId) throws ResourceNotFound;
}
