package com.cg.dto;

import com.cg.entity.Customer;
import com.cg.entity.Payment;

public final class Mapper {

    private Mapper() {}

    // Convert Customer entity → CustomerDTO
    public static CustomerDTO toCustomerDTO(Customer c) {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(c.getCustomerId());
        dto.setCustomerName(c.getCustomerName());
        dto.setContacts(c.getContacts());
        dto.setEmail(c.getEmail());
        dto.setAddress(c.getAddress());
        return dto;
    }

    // Update Customer entity using DTO values
    public static void updateCustomerFromDTO(CustomerDTO dto, Customer c) {
        c.setCustomerName(dto.getCustomerName());
        c.setContacts(dto.getContacts());
        c.setEmail(dto.getEmail());
        c.setAddress(dto.getAddress());
    }

    // Convert Payment entity → PaymentDTO
    public static PaymentDTO toPaymentDTO(Payment p) {
        PaymentDTO dto = new PaymentDTO();
        dto.setPaymentId(p.getPaymentId());
        dto.setPaymentName(p.getPaymentName());
        dto.setAmount(p.getAmount());
        dto.setTransactionStatus(p.getTransactionStatus());
        dto.setCustomerId(p.getCustomer().getCustomerId());
        return dto;
    }
}