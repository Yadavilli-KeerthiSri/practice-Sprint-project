package com.cg.controller;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.DeliveryAgent;
import com.cg.service.DeliveryAgentService;
 
@RestController
@RequestMapping("delivery-agent")
public class DeliveryAgentController {
 
    @Autowired
    private DeliveryAgentService deliveryAgentService;
 
    @PostMapping("/add")
    public DeliveryAgent addAgent(@RequestBody DeliveryAgent agent) {
        return deliveryAgentService.addAgent(agent);
    }
 
    @GetMapping("/all")
    public List<DeliveryAgent> getAllAgents() {
        return deliveryAgentService.getAllAgents();
    }
 
    @GetMapping("/available")
    public List<DeliveryAgent> getAvailableAgents() {
        return deliveryAgentService.getAvailableAgents();
    }
 
    @PutMapping("/availability/{id}")
    public DeliveryAgent updateAvailability(@PathVariable Long id,
                                            @RequestParam boolean status) {
        return deliveryAgentService.updateAvailability(id, status);
    }
}