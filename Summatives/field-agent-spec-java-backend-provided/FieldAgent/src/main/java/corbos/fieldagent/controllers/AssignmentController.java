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
    
    @GetMapping("/addAssignmentErrors")
    public String addAssignmentErrors(Model model){
        return "addAssignmentErrors";
    }
    
    @GetMapping("/editAssignment")
    public String editAssignment(String id, Model model){
        // add lists to the model
        List<Agent> agentList = lookupService.findAllAgents();
        List<Country> countryList = lookupService.findAllCountries();
        Assignment assignment = lookupService.findAssignmentById(Integer.parseInt(id));
        model.addAttribute("agentList", agentList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("assignment", assignment);
        return "editAssignment";
    }
    
    @PostMapping("/editAssignment")
    public String performEditAssignment(@Valid Assignment assignment, BindingResult result, HttpServletRequest request){
        // set properties that are objects (don't need any if->null validation)
        assignment.setCountry(lookupService.findCountryByCode(request.getParameter("countryCode")));
        assignment.setAgent(lookupService.findAgentByIdentifier(request.getParameter("agentIdentifier")));
        
        if(assignment.getStartDate() == null){
            result.addError(new FieldError("assignment", "startDate", "Must enter a start date."));
        }
        if(assignment.getProjectedEndDate() == null){
            result.addError(new FieldError("assignment", "projectedEndDate", "Must enter a projected end date."));
        }
        if(assignment.getActualEndDate() == null){
            result.addError(new FieldError("assignment", "actualEndDate", "Must enter an actual end date."));
        }
        
        // ADD IN DATE-SPECIFICS VALIDATION
        
        if (result.hasErrors()){
            return "editAssignment";
        }
        
        addService.addUpdateAssignment(assignment);
        return "redirect:/";
    }
    
    @GetMapping("/deleteAssignment")
    public String deleteAssignment(String id){
        // get Agent's indentifier that assignment was assigned to
        Assignment assignment = lookupService.findAssignmentById(Integer.parseInt(id));
        String agentIdentifier = assignment.getAgent().getIdentifier();
        
        // delete assignment
        deleteService.deleteAssignment(Integer.parseInt(id));
        return "redirect:/editAgent?id=" + agentIdentifier;
    }
    
}
