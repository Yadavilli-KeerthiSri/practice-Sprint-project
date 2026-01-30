package com.cg.dto;

public class DeliveryAgentDTO {
    private Long agentId;
    private String agentName;
    private String contact;
    private String vehicleDetails;
    private Boolean availability;

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
