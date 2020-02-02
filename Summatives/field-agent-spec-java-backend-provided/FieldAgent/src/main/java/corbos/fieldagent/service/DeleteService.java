package corbos.fieldagent.service;

import corbos.fieldagent.data.AgencyRepository;
import corbos.fieldagent.data.AgentRepository;
import corbos.fieldagent.data.AssignmentRepository;
import corbos.fieldagent.data.CountryRepository;
import corbos.fieldagent.data.SecurityClearanceRepository;
import corbos.fieldagent.entities.Assignment;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author jake
 */

@Service
public class DeleteService {

    private final AgentRepository agentRepo;
    private final AssignmentRepository assignmentRepo;
    private final AgencyRepository agencyRepo;
    private final CountryRepository countryRepo;
    private final SecurityClearanceRepository securityRepo;

    public DeleteService(AgentRepository agentRepo, 
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
    
    public void deleteAgent(String agentIdentifier){
        // get all assignments associated with agent
        List<Assignment> assignments = assignmentRepo.findByAgentIdentifier(agentIdentifier);
        // delete all assignments associated with agent
        for (Assignment assignment : assignments){
            assignmentRepo.delete(assignment);
        }
        // delete agent
        agentRepo.delete(agentRepo.findById(agentIdentifier).orElse(null));
    }
}
