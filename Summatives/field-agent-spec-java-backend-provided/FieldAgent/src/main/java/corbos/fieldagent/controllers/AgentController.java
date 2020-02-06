package corbos.fieldagent.controllers;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.AddService;
import corbos.fieldagent.service.DeleteService;
import corbos.fieldagent.service.LookupService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jake
 */

@Controller
public class AgentController {

    @Autowired
    LookupService lookupService;
    
    @Autowired
    AddService addService;
    
    @Autowired
    DeleteService deleteService;
    
    // create a set of violations to add to in methods
    Set<ConstraintViolation<Agent>> violations = new HashSet();
    Set<String> otherViolations = new HashSet();
    
    @GetMapping({"/", "/home", "/agent/home"})
    public String displayHomepage(Model model){
        violations.clear();
        otherViolations.clear();
        List<Agent> agentList = lookupService.findAllAgents();
        model.addAttribute("agentList", agentList);
        return "home";
    }
    
    @GetMapping("/addAgent")
    public String addAgent(Model model){
        List<Agency> agencyList = lookupService.findAllAgencies();
        List<SecurityClearance> clearanceList = lookupService.findAllSecurityClearances();
        model.addAttribute("agencyList", agencyList);
        model.addAttribute("clearanceList", clearanceList);
        model.addAttribute("errors", violations);
        model.addAttribute("customErrors", otherViolations);
        return "addAgent";
    }
    
    @PostMapping("/addAgent")
    public String performAddAgent(Agent agent, HttpServletRequest request){
        // set agent's agency and clearance properties by looking up their IDs
        if (request.getParameter("agencyId") != null){
            agent.setAgency(lookupService.findAgencyById(Integer.parseInt(request.getParameter("agencyId"))));
        }
        if (request.getParameter("securityClearanceId") != null){
            agent.setSecurityClearance(lookupService.findSecurityClearanceById(Integer.parseInt(request.getParameter("securityClearanceId"))));
        }
        
        Validator validatorObj = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validatorObj.validate(agent);
        otherViolations = addService.validateAgent(agent);
        // validate Agent doesn't already exist
        if (!addService.validateAgentDoesNotExist(agent)){
            otherViolations.add("Agent identifier already exists");
        }
        
        if(!violations.isEmpty() || !otherViolations.isEmpty()){
            return "redirect:/addAgent";
        }
        addService.addUpdateAgent(agent);
        return "redirect:/";
    }
    
    @GetMapping("/editAgent")
    public String editAgent(String id, Model model){
        List<Agency> agencyList = lookupService.findAllAgencies();
        List<SecurityClearance> clearanceList = lookupService.findAllSecurityClearances();
        List<Assignment> assignmentList = lookupService.findAssignmentsByAgent(id);
        model.addAttribute("agencyList", agencyList);
        model.addAttribute("clearanceList", clearanceList);
        model.addAttribute("assignmentList", assignmentList);
        model.addAttribute("errors", violations);
        model.addAttribute("customErrors", otherViolations);
        
        // add Agent to model for editing
        Agent agent = lookupService.findAgentByIdentifier(id);
        model.addAttribute("agent", agent);
        model.addAttribute("isActive", agent.isActive());
        return "editAgent";
    }
    
    @PostMapping("/editAgent")
    public String performEditAgent(Agent agent, HttpServletRequest request){
        // Don't need to be validated as being not null when editing
        agent.setAgency(lookupService.findAgencyById(Integer.parseInt(request.getParameter("agencyId"))));
        agent.setSecurityClearance(lookupService.findSecurityClearanceById(Integer.parseInt(request.getParameter("securityClearanceId"))));
        // didn't think I would have to write this but it makes it work
        agent.setActive(Boolean.parseBoolean(request.getParameter("isActive")));
        
        Validator validatorObj = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validatorObj.validate(agent);
        otherViolations = addService.validateAgent(agent);
        
        if (!violations.isEmpty() || !otherViolations.isEmpty()){
            return "redirect:/editAgent?id=" + agent.getIdentifier();
        }
        
        addService.addUpdateAgent(agent);
        return "redirect:/";
    }
    
    // render delete confirmation page
    @GetMapping("/deleteConfirmation")
    public String deleteAgent(String id, Model model){
        // get Agent object
        Agent agent = lookupService.findAgentByIdentifier(id);
        
        // get Assignments associated with Agent
        List<Assignment> assignmentList = lookupService.findAssignmentsByAgent(id);
        
        model.addAttribute("agent", agent);
        model.addAttribute("assignmentNumber", assignmentList.size());
        return "deleteConfirmation";
    }
    
    @GetMapping("/deleteAgent")
    public String performDeleteAgent(String id){
        deleteService.deleteAgent(id);
        return "redirect:/";
    }
}
