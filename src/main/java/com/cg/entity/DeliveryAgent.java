package com.cg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "delivery_agent")
public class DeliveryAgent {
 
    @Id
    private Long agentId;
 
    private String agentName;
    private String contact;
    private String vehicleDetails;
    private boolean availability;
 
    // Constructors
    public DeliveryAgent() {
    }
 
    public DeliveryAgent(Long agentId, String agentName, String contact,
                         String vehicleDetails, boolean availability) {
        this.agentId = agentId;
        this.agentName = agentName;
        this.contact = contact;
        this.vehicleDetails = vehicleDetails;
        this.availability = availability;
    }
 
    // Getters and Setters
    public Long getAgentId() {
        return agentId;
    }
 
    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }
 
    public String getAgentName() {
        return agentName;
    }
 
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
 
    public String getContact() {
        return contact;
    }
 
    public void setContact(String contact) {
        this.contact = contact;
    }
 
    public String getVehicleDetails() {
        return vehicleDetails;
    }
 
    public void setVehicleDetails(String vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
 
    public boolean isAvailability() {
        return availability;
    }
 
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}