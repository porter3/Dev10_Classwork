package com.jakeporter.serverinventory.dao;

import com.jakeporter.serverinventory.dto.Server;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        ServerDao dao = new ServerDaoInMemImpl();
        
        Server myServer = new Server();
        myServer.setName("web01");
        myServer.setIp("192.168.1.1");
        myServer.setManufacturer("Dell");
        myServer.setRam(8);
        myServer.setNumProcessors(9);
        myServer.setPurchaseDate(LocalDate.parse("2010-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        myServer = new Server();
        myServer.setName("db01");
        myServer.setIp("192.168.3.45");
        myServer.setManufacturer("HP");
        myServer.setRam(16);
        myServer.setNumProcessors(24);
        myServer.setPurchaseDate(LocalDate.parse("2013-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        myServer = new Server();
        myServer.setName("hr124");
        myServer.setIp("192.168.32.111");
        myServer.setManufacturer("IBM");
        myServer.setRam(16);
        myServer.setNumProcessors(12);
        myServer.setPurchaseDate(LocalDate.parse("2014-01-01", DateTimeFormatter.ISO_DATE));
        
        dao.addServer(myServer);
        
        // get list of servers manufactured by Dell
        List<Server> dells = dao.getServersByManufacturer("Dell");
        for (Server currentServer : dells){
            System.out.println(currentServer.getName());
        }
        
        dells.stream()
                .forEach(s -> System.out.println(s.getName()));
        
        Map<String, List<Server>> serverMap = dao.getAllServersGroupByManufacturer();
        // print name of manufacturer, then list all servers by that manufacturer, REPEAT
        Set<String> manufacturers = serverMap.keySet();
        
        manufacturers.stream()
                .forEach(name -> {  System.out.println("========================");
                                    System.out.println("Manufacturer: " + name);
                                    serverMap.get(name).stream().forEach(s -> System.out.println(s.getName()));
                });
    }
}
