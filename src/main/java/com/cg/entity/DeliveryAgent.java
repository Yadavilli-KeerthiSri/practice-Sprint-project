package com.cg.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "delivery_agents",
       indexes = {
           @Index(name = "idx_delivery_agent_availability", columnList = "availability"),
           @Index(name = "idx_delivery_agent_name", columnList = "agent_name")
       })
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "agent_Id") // keeping your exact case as requested
    private Long agentId;

    @NotBlank(message = "Agent name is required")
    @Column(name = "agent_name", nullable = false, length = 100)
    private String agentName;

    // 10-digit number pattern; adjust based on your region if needed
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact must be a 10-digit number")
    @Column(name = "Contact", length = 15, unique = true)
    private String contact;

    @Column(name = "Vehicle_details", length = 200)
    private String vehicleDetails;

    @Column(name = "Availability", nullable = false)
    private Boolean availability = Boolean.TRUE;

    // === Optional: If Orders team wants to map deliveries to an agent via Order ===
    // They can add a @ManyToOne in Order:
    // @OneToMany(mappedBy = "deliveryAgent")
    // private List<Order> orders;

    // Getters and Setters
    public Long getAgentId() { return agentId; }
    public void setAgentId(Long agentId) { this.agentId = agentId; }

    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    public String getVehicleDetails() { return vehicleDetails; }
    public void setVehicleDetails(String vehicleDetails) { this.vehicleDetails = vehicleDetails; }

    public Boolean getAvailability() { return availability; }
    public void setAvailability(Boolean availability) { this.availability = availability; }
}