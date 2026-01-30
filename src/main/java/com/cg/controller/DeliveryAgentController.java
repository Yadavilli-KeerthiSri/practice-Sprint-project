package com.cg.controller;

import com.cg.dto.CreateDeliveryAgentRequest;
import com.cg.dto.DeliveryAgentDTO;
import com.cg.service.DeliveryAgentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class DeliveryAgentController {

    private final DeliveryAgentService service;

    public DeliveryAgentController(DeliveryAgentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DeliveryAgentDTO> create(@Valid @RequestBody CreateDeliveryAgentRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAgentDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAgentDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<DeliveryAgentDTO>> listAvailable() {
        return ResponseEntity.ok(service.listAvailable());
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<DeliveryAgentDTO> updateAvailability(@PathVariable Long id,
                                                               @RequestParam boolean available) {
        return ResponseEntity.ok(service.updateAvailability(id, available));
    }

    @PatchMapping("/{id}/vehicle")
    public ResponseEntity<DeliveryAgentDTO> updateVehicle(@PathVariable Long id,
                                                          @RequestParam String vehicleDetails) {
        return ResponseEntity.ok(service.updateVehicle(id, vehicleDetails));
    }
}