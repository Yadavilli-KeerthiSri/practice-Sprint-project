package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dto.CreateDeliveryAgentRequest;
import com.cg.dto.DeliveryAgentDTO;
import com.cg.entity.DeliveryAgent;
import com.cg.repository.DeliveryAgentRepository;

@Service
@Transactional
public class DeliveryAgentService {
    private final DeliveryAgentRepository repo;

    public DeliveryAgentService(DeliveryAgentRepository repo) {
        this.repo = repo;
    }

    public DeliveryAgentDTO create(CreateDeliveryAgentRequest req) {
        if (repo.existsByContact(req.getContact())) {
            throw new IllegalArgumentException("Contact already registered for another agent");
        }
        DeliveryAgent da = new DeliveryAgent();
        da.setAgentName(req.getAgentName());
        da.setContact(req.getContact());
        da.setVehicleDetails(req.getVehicleDetails());
        da.setAvailability(req.getAvailability() != null ? req.getAvailability() : Boolean.TRUE);

        da = repo.save(da);
        return toDTO(da);
    }

    public DeliveryAgentDTO get(Long id) {
        DeliveryAgent da = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Delivery agent not found"));
        return toDTO(da);
    }

    public List<DeliveryAgentDTO> listAll() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public List<DeliveryAgentDTO> listAvailable() {
        return repo.findByAvailabilityTrue().stream().map(this::toDTO).toList();
    }

    public DeliveryAgentDTO updateAvailability(Long agentId, boolean available) {
        DeliveryAgent da = repo.findById(agentId)
            .orElseThrow(() -> new IllegalArgumentException("Delivery agent not found"));
        da.setAvailability(available);
        return toDTO(repo.save(da));
    }

    public DeliveryAgentDTO updateVehicle(Long agentId, String vehicleDetails) {
        DeliveryAgent da = repo.findById(agentId)
            .orElseThrow(() -> new IllegalArgumentException("Delivery agent not found"));
        da.setVehicleDetails(vehicleDetails);
        return toDTO(repo.save(da));
    }

    private DeliveryAgentDTO toDTO(DeliveryAgent da) {
        DeliveryAgentDTO dto = new DeliveryAgentDTO();
        dto.setAgentId(da.getAgentId());
        dto.setAgentName(da.getAgentName());
        dto.setContact(da.getContact());
        dto.setVehicleDetails(da.getVehicleDetails());
        dto.setAvailability(da.getAvailability());
        return dto;
    }
}
