
import com.jakeporter.vendingmachine.controller.VendingMachineController;
import com.jakeporter.vendingmachine.dao.VendingMachineAuditDao;
import com.jakeporter.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.jakeporter.vendingmachine.dao.VendingMachineDao;
import com.jakeporter.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.jakeporter.vendingmachine.service.VendingMachineServiceLayer;
import com.jakeporter.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.jakeporter.vendingmachine.ui.UserIO;
import com.jakeporter.vendingmachine.ui.UserIOConsoleImpl;
import com.jakeporter.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
//        
//        // new userio
//        UserIO io = new UserIOConsoleImpl();
//        // new view
//        VendingMachineView view = new VendingMachineView(io);
//        // new dao, audit dao
//        VendingMachineDao crudDao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
//        // new service
//        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(crudDao, auditDao);
//        // new controller
//        VendingMachineController controller = new VendingMachineController(service, view);
//        
//        controller.run();

        // isntantiate the Spring container that hosts the framework components
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        // instantiate controller
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
