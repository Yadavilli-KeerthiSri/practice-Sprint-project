package com.cg.service;
 
import java.util.List;

import com.cg.entity.DeliveryAgent;
 
public interface DeliveryAgentService {
 
    DeliveryAgent addAgent(DeliveryAgent agent);
 
    List<DeliveryAgent> getAllAgents();
 
    List<DeliveryAgent> getAvailableAgents();
 
    DeliveryAgent updateAvailability(Long agentId, boolean availability);
}