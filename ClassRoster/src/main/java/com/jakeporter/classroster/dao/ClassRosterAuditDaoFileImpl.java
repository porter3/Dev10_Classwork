package com.jakeporter.classroster.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author jake
 */
public class ClassRosterAuditDaoFileImpl implements ClassRosterAuditDao{
    
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException {
        PrintWriter out;
        
        try{
            // the 'true' argument in FileWriter set the bytes to be written at the end of the file rather than beginning
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }
        catch(IOException e){
            throw new ClassRosterPersistenceException("Could not persist audit information.", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        // .toString() here may be superfluous
        out.println(timestamp + ": " + entry);
        out.flush();
    }
    
}
