package com.jakeporter.serverinventory.dao;

import com.jakeporter.serverinventory.dto.Server;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author jake
 */
public class ServerDaoInMemImpl implements ServerDao{
    
    private Map<String, Server> serverMap = new HashMap();

    @Override
    public void addServer(Server server) {
        serverMap.put(server.getName(), server);
    }

    @Override
    public Server getServer(String name) {
        return serverMap.get(name);
    }

    @Override
    public void removeServer(String name) {
        serverMap.remove(name);
    }

    @Override
    public List<Server> getAllServers() {
        return new ArrayList<Server>(serverMap.values());
    }

    @Override
    public Map<String, List<Server>> getAllServersGroupByManufacturer() {
        // get stream of values in serverMap, NO FILTER, no intermediate operations, just one terminal one
        return serverMap.values() // servers
                .stream() // stream of servers
                // TERMINAL OPERATION
                .collect(Collectors.groupingBy(s -> s.getManufacturer())); // for every server that comes through, call getManufacturer() on it
                                            // could also be Server::getManufacturer
    }

    @Override
    public List<Server> getServersByManufacturer(String manufacturer) {
        // filter the servers by manufacturer
        return serverMap.values()
                // a stream is just a pipeline of objects/values that you can perform specific operations on
                .stream()
                // the predicate is the criteria applied for the filter
                // THIS IS AN INTERMEDIATE STEP
                .filter(s -> s.getManufacturer().equalsIgnoreCase(manufacturer)) // only pass through Servers made by 'manufacturer'
                // collect any stream objects that get through the filter into a list and return them
                .collect(Collectors.toList());
    }

    @Override
    public List<Server> getServersOlderThan(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears) // for the expressions that return 'true', the filter will let them pass through
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Server>> getServersOlderThanGroupByManufacturer(int ageInYears) {
        return serverMap.values()
                .stream()
                .filter(s -> s.getServerAge() > ageInYears)
                .collect(Collectors.groupingBy(s -> s.getManufacturer()));
    }

    @Override
    public double getAverageServerAge() {
        return serverMap.values()
                .stream()
                // transform a stream of servers into a stream of longs, then pass it into an operation that will average them, returning it as a double
                .mapToLong(s -> s.getServerAge()) // extract server age from each Server (turning it into a long)
                .average()
                .getAsDouble();
    }

    
}
