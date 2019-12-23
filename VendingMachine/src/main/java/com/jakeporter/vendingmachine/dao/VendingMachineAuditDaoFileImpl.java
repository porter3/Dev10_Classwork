package com.jakeporter.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author jake
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao{
        
    private static final String AUDIT_FILE = "inventoryAudit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws InventoryPersistenceException {
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }
        catch(IOException e){
            throw new InventoryPersistenceException("Could not write audit info to file", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp + ": " + entry);
        out.flush();
        out.close();
    }

}
