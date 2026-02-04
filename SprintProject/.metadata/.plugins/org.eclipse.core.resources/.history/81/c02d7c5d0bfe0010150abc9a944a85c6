package com.cg.service;

import com.cg.dto.Mapper;
import com.cg.dto.PaymentDTO;
import com.cg.entity.Customer;
import com.cg.entity.Payment;
import com.cg.entity.TransactionStatus;
import com.cg.exception.ResourceNotFoundException;
import com.cg.iservice.IPaymentService;
import com.cg.repository.ICustomerRepository;
import com.cg.repository.IPaymentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;
    private final ICustomerRepository customerRepository;

    // Constructor injection (recommended)
    public PaymentService(IPaymentRepository paymentRepository,
                              ICustomerRepository customerRepository) {
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public PaymentDTO create(PaymentDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + dto.getCustomerId()));

        // No @Builder: use plain object + setters
        Payment payment = new Payment();
        payment.setPaymentName(dto.getPaymentName());
        payment.setAmount(dto.getAmount());
        payment.setTransactionStatus(dto.getTransactionStatus());
        payment.setCustomer(customer);

        return Mapper.toPaymentDTO(paymentRepository.save(payment));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentDTO getById(Long id) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found: " + id));
        return Mapper.toPaymentDTO(p);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getByCustomer(Long customerId) {
        return paymentRepository.findByCustomer_CustomerId(customerId)
                .stream()
                .map(Mapper::toPaymentDTO)
                .toList();
    }

    @Override
    public PaymentDTO updateStatus(Long paymentId, String newStatus) {
        Payment p = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found: " + paymentId));

        TransactionStatus status = TransactionStatus.valueOf(newStatus.toUpperCase());
        p.setTransactionStatus(status);

        return Mapper.toPaymentDTO(paymentRepository.save(p));
    }

    @Override
    public void delete(Long id) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found: " + id));
        paymentRepository.delete(p);
    }
    @Override
    @Transactional(readOnly = true)
    public List<PaymentDTO> getAll() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDTO> dtoList = new ArrayList<>();
        
        for (Payment payment : payments) {
            dtoList.add(Mapper.toPaymentDTO(payment));
        }
        
        return dtoList;
    }

}