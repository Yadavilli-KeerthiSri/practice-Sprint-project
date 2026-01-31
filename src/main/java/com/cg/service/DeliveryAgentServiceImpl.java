package com.cg.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.DeliveryAgent;
import com.cg.repository.DeliveryAgentRepository;
 
@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {
 
    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;
 
    @Override
    public DeliveryAgent addAgent(DeliveryAgent agent) {
        return deliveryAgentRepository.save(agent);
    }
 
    @Override
    public List<DeliveryAgent> getAllAgents() {
        return deliveryAgentRepository.findAll();
    }
 
    @Override
    public List<DeliveryAgent> getAvailableAgents() {
        return deliveryAgentRepository.findByAvailabilityTrue();
    }
 
    @Override
    public DeliveryAgent updateAvailability(Long agentId, boolean availability) {
        DeliveryAgent agent = deliveryAgentRepository.findById(agentId).orElse(null);
 
        if (agent != null) {
            agent.setAvailability(availability);
            return deliveryAgentRepository.save(agent);
        }
        return null;
    }
}