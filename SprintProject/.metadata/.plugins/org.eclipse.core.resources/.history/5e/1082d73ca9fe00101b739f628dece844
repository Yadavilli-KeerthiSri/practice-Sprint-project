package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.entity.DeliveryAgent;
import com.cg.exception.ResourceNotFound;
import com.cg.service.DeliveryAgentService;

@Controller
@RequestMapping("/agents")
public class DeliveryAgentController {
	@Autowired
    private DeliveryAgentService agentService;
 
    @GetMapping
    public String getAllAgents(Model model) {
        model.addAttribute("agents", agentService.getAllAgents());
        return "agents";
    }
 
    @PostMapping("/add")
    public String addAgent(DeliveryAgent agent) {
        agentService.createAgent(agent);
        return "redirect:/agents";
    }
 
    @PostMapping("/update/{id}")
    public String updateAgent(@PathVariable Integer id, DeliveryAgent agent) throws ResourceNotFound {
        agentService.updateAgent(id, agent);
        return "redirect:/agents";
    }
 
    @GetMapping("/delete/{id}")
    public String deleteAgent(@PathVariable Integer id) throws ResourceNotFound {
        agentService.deleteAgent(id);
        return "redirect:/agents";
    }
}
