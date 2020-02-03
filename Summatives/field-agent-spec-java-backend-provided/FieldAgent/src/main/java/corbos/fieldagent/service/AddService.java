package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.entities.Assignment;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author jake
 */

@Service
public class AddService {

    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;
    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;

    public AddService(AgentRepository agentRepo, 
            AssignmentRepository assignmentRepo, 
            AgencyRepository agencyRepo,
            CountryRepository countryRepo,
            SecurityClearanceRepository securityRepo) {
        this.agentRepo = agentRepo;
        this.assignmentRepo = assignmentRepo;
        this.agencyRepo = agencyRepo;
        this.countryRepo = countryRepo;
        this.securityRepo = securityRepo;
    }
    
    public Agent addUpdateAgent(Agent agent){
        return agentRepo.save(agent);
    }
    
    public Assignment addUpdateAssignment(Assignment assignment){
        return assignmentRepo.save(assignment);
    }
    
    public Set<String> validateAgent(Agent agent){
        Set<String> violations = new HashSet();
        LocalDate birthDate = agent.getBirthDate();

        // birthDate validation
        LocalDate tenYearsFromCurrentDay = LocalDate.now().minusYears(10);
        // date must be entered
        if (birthDate == null){
            violations.add("Birth Date must be entered");
        }
        // date must be ten years ago from today
        else if (birthDate.isAfter(tenYearsFromCurrentDay)){
            violations.add("Birth Date cannot be within the last 10 years");
        }
        // HTML 'min' attribute currently prevents this from being necessary
        else if (birthDate.isBefore(LocalDate.of(1900, Month.JANUARY, 1))){
            violations.add("Birth Date must be after 01/01/1900");
        }
        if (agent.getActivationDate() == null){
            violations.add("Must enter an activation date");
        }
        // activationDate validation - not formally required but seems like common sense to have
        else if (agent.getActivationDate().isBefore(birthDate)){
            violations.add("An agent cannot be activated before they're born, unless you have a time machine");
        }
        // agency validation
        if (agent.getAgency() == null){
            violations.add("An agency must be selected");
        }
        // security clearance validation
        if (agent.getSecurityClearance() == null){
            violations.add("A security clearance type must be selected");
        }
        return violations;
    }
    
    public boolean validateAgentDoesNotExist(Agent agentToValidate){
        List<Agent> agents = agentRepo.findAll();
        List<String> identifiers = new ArrayList();
        for (Agent agent : agents){
            identifiers.add(agent.getIdentifier());
        }
        if (identifiers.contains(agentToValidate.getIdentifier())){
            return false;
        }
        return true;
    }
    
    public Set<String> validateAssignment(Assignment assignment){
        Set<String> violations = new HashSet();
        
        // validate country
        if (assignment.getCountry() == null){
            violations.add("Must select a country");
        }
        LocalDate startDate = assignment.getStartDate();
        LocalDate projectedEndDate = assignment.getProjectedEndDate();
        LocalDate actualEndDate = assignment.getActualEndDate();
        // validate startDate existence
        if (startDate == null){
            violations.add("Must enter a start date");
        }
        // validate projectedEndDate exists and is before start date
        if (projectedEndDate == null){
            violations.add("Must enter a projected end date");
        }
        else if (projectedEndDate.isBefore(startDate)){
            violations.add("Projected end date must be after start date");
        }
        // validate actualEndDate exists and is before start date        
        if (actualEndDate == null){
            violations.add("Must enter an actual end date");
        }
        else if (actualEndDate.isBefore(startDate)){
            violations.add("Actual end date must be after start date");
        }

        return violations;
    }
}
