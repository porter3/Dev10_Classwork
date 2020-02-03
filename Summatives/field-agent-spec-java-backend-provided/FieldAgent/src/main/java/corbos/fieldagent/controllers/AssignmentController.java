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
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    Set<ConstraintViolation<Assignment>> violations = new HashSet();
    Set<String> otherViolations = new HashSet();
    
    @GetMapping("/addAssignment")
    public String addAssignment(String id, Model model){
        // get model attributes
        model.addAttribute("agentForAssignment", lookupService.findAgentByIdentifier(id));
        model.addAttribute("agentList", lookupService.findAllAgents());
        model.addAttribute("countryList", lookupService.findAllCountries());
        
        model.addAttribute("errors", violations);
        model.addAttribute("customErrors", otherViolations);
        
        return "addAssignment";
    }
    
    @PostMapping("/addAssignment")
    public String peformAddAssignment(Assignment assignment, HttpServletRequest request, Model model){
        if(request.getParameter("agentIdentifier") != null){
            assignment.setAgent(lookupService.findAgentByIdentifier(request.getParameter("agentIdentifier")));
        }
        if(request.getParameter("countryCode") != null){
            assignment.setCountry(lookupService.findCountryByCode(request.getParameter("countryCode")));
        }
        
        Validator validatorObj = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validatorObj.validate(assignment);
        otherViolations = addService.validateAssignment(assignment);
        
        if (!violations.isEmpty() || !otherViolations.isEmpty()){
            return "redirect:/addAssignment?id=" + assignment.getAgent().getIdentifier();
        }
        addService.addUpdateAssignment(assignment);
        return "redirect:/";
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
        model.addAttribute("errors", violations);
        model.addAttribute("customErrors", otherViolations);
        return "editAssignment";
    }
    
    @PostMapping("/editAssignment")
    public String performEditAssignment(@Valid Assignment assignment, BindingResult result, HttpServletRequest request){
        // set properties that are objects (don't need any if->null validation)
        assignment.setCountry(lookupService.findCountryByCode(request.getParameter("countryCode")));
        assignment.setAgent(lookupService.findAgentByIdentifier(request.getParameter("agentIdentifier")));
        
        Validator validatorObj = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validatorObj.validate(assignment);
        otherViolations = addService.validateAssignment(assignment);
                
        if (!violations.isEmpty() || !otherViolations.isEmpty()){
            return "redirect:/editAssignment?id=" + assignment.getAssignmentId();
        }
        
        addService.addUpdateAssignment(assignment);
        return "redirect:/editAgent?id=" + assignment.getAgent().getIdentifier();
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
