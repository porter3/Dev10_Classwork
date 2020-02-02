package corbos.fieldagent.controllers;

import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import corbos.fieldagent.entities.Country;
import corbos.fieldagent.service.AddService;
import corbos.fieldagent.service.DeleteService;
import corbos.fieldagent.service.LookupService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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
public class AssignmentController {

    @Autowired
    LookupService lookupService;
    
    @Autowired
    AddService addService;
    
    @Autowired
    DeleteService deleteService;
    
    // create a set of violations to add to in methods
    Set<ConstraintViolation<Agent>> violations = new HashSet();
    
    @GetMapping("/addAssignment")
    public String addAssignment(String id, Model model){
        
        // get Agent to presumably add assignment for
        Agent agent = lookupService.findAgentByIdentifier(id);
        model.addAttribute("agentForAssignment", agent);
        
        // get agents to look up for assignment
        List<Agent> agentList = lookupService.findAllAgents();
        model.addAttribute("agentList", agentList);
        
        // get list of countries
        List<Country> countryList = lookupService.findAllCountries();
        model.addAttribute("countryList", countryList);
        
        return "addAssignment";
    }
    
    @PostMapping("/addAssignment")
    public String peformAddAssignment(@Valid Assignment assignment, BindingResult result, HttpServletRequest request, Model model){
        // check for null values and set all unassignment properties of assignment
        if (request.getParameter("countryCode") == null){
            result.addError(new FieldError("assignment", "country", "Must select a country."));
        }
        else{
            assignment.setCountry(lookupService.findCountryByCode(request.getParameter("countryCode")));
        }
        if (request.getParameter("agentIdentifier") == null){
            result.addError(new FieldError("assignment", "agent", "Must select an agent."));
        }
        else{
            assignment.setAgent(lookupService.findAgentByIdentifier(request.getParameter("agentIdentifier")));
        }
        
        System.out.println("ASSIGNMENT INFO: " + assignment.toString());
        
        // validation for all date fields
        if (assignment.getStartDate() == null){
            result.addError(new FieldError("assignment", "startDate", "Must enter a start date."));
        }
        if (assignment.getProjectedEndDate() == null){
            result.addError(new FieldError("assignment", "projectedEndDate", "Must enter a projected end date."));
        }
        if (assignment.getActualEndDate() == null){
            result.addError(new FieldError("assignment", "actualEndDate", "Must enter an actual end date."));
        }
        
        if (result.hasErrors()){
            return "redirect:/addAssignmentErrors";
        }
        addService.addUpdateAssignment(assignment);
        return "redirect:/";
    }
    
    @GetMapping("addAssignmentErrors")
    public String addAssignmentErrors(Model model){
        return "addAssignmentErrors";
    }
}
