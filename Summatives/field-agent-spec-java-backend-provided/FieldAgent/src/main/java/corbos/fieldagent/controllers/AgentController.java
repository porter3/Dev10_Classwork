package corbos.fieldagent.controllers;

import corbos.fieldagent.entities.Agency;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.SecurityClearance;
import corbos.fieldagent.service.AddService;
import corbos.fieldagent.service.DeleteService;
import corbos.fieldagent.service.LookupService;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
        
    @GetMapping("/addAgent")
    public String addAgent(Model model){
        List<Agency> agencyList = lookupService.findAllAgencies();
        List<SecurityClearance> clearanceList = lookupService.findAllSecurityClearances();
        model.addAttribute("agencyList", agencyList);
        model.addAttribute("clearanceList", clearanceList);
        return "addAgent";
    }
    
    @PostMapping("/addAgent")
    public String performAddAgent(@Valid Agent agent, BindingResult result, HttpServletRequest request, Model model){
        // set agent's agency and clearance properties by looking up their IDs
        agent.setAgency(lookupService.findAgencyById(Integer.parseInt(request.getParameter("agencyId"))));
        agent.setSecurityClearance(lookupService.findSecurityClearanceById(Integer.parseInt(request.getParameter("securityClearanceId"))));
        // if agent didn't pass validation:
        model.addAttribute("errors", violations);

        System.out.println("AGENT INFO: " + agent.toString());
        
        // add validation for birthDate existence
        LocalDate tenYearsFromCurrentDay = LocalDate.now().minusYears(10);
        // date must be entered
        if (agent.getBirthDate() == null){
            result.addError(new FieldError("agent", "birthDate", "Birth Date must be entered."));
        }
        // date must be ten years ago from today
        else if (agent.getBirthDate().isAfter(tenYearsFromCurrentDay)){
            result.addError(new FieldError("agent", "birthDate", "Birth Date must be earlier than 10 years ago from current date."));
        }
                
        if(result.hasErrors()){
            return "redirect:/addAgentErrors";
        }
        addService.addUpdateAgent(agent);
        return "redirect:/";
    }
    
    @GetMapping("/addAgentErrors")
    public String addAgentErrors(Model model){
        return "addAgentErrors";
    }
    
    @GetMapping("/editAgent")
    public String editAgent(String id, Model model){
        List<Agency> agencyList = lookupService.findAllAgencies();
        List<SecurityClearance> clearanceList = lookupService.findAllSecurityClearances();
        model.addAttribute("agencyList", agencyList);
        model.addAttribute("clearanceList", clearanceList);
        
        // add Agent to model for editing
        Agent agent = lookupService.findAgentByIdentifier(id);
        model.addAttribute("agent", agent);
        return "editAgent";
    }
    
    @PostMapping("/editAgent")
    public String performEditAgent(@Valid Agent agent, BindingResult result, HttpServletRequest request){
        
        agent.setAgency(lookupService.findAgencyById(Integer.parseInt(request.getParameter("agencyId"))));
        agent.setSecurityClearance(lookupService.findSecurityClearanceById(Integer.parseInt(request.getParameter("securityClearanceId"))));
        
        // ADD IN VALIDATION
        
        if (result.hasErrors()){
            return "editAgent";
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
