package corbos.fieldagent.controllers;

import corbos.fieldagent.entities.Agent;
import corbos.fieldagent.service.LookupService;
import org.springframework.stereotype.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jake
 */

@Controller
public class HomeController {
    
    @Autowired
    LookupService lookupService;

    @GetMapping({"/", "/home"})
    public String displayHomepage(Model model){
        List<Agent> agentList = lookupService.findAllAgents();
        model.addAttribute("agentList", agentList);
        return "home";
    }
}
