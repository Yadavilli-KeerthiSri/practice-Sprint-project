package com.cg.iservice;

import java.util.List;

import com.cg.entity.DeliveryAgent;
import com.cg.exception.ResourceNotFound;

public interface IDeliveryAgentService {
	DeliveryAgent createAgent(DeliveryAgent agent);
	 
    DeliveryAgent updateAgent(Integer id, DeliveryAgent agent) throws ResourceNotFound;
 
    DeliveryAgent getAgentById(Integer id) throws ResourceNotFound;
 
    List<DeliveryAgent> getAllAgents();
 
    void deleteAgent(Integer id) throws ResourceNotFound;
}
