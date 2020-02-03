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
import java.util.HashSet;
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
            violations.add("Birth Date cannot be within the last 10 years.");
        }
        
        return violations;
    }
}
