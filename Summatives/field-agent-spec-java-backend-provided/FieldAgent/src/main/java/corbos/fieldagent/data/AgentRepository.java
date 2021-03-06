package corbos.fieldagent.data;

import corbos.fieldagent.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository
        extends JpaRepository<Agent, String> {
}
