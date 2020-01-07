package com.jakeporter.mp3library.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author jake
 */
public class Mp3LibraryAuditDaoFileImpl implements Mp3LibraryAuditDao{

    private static final String AUDIT_FILE = "mp3Audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws Mp3LibraryPersistenceException {
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }
        catch(IOException e){
            throw new Mp3LibraryPersistenceException("Could not write audit info to file", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp + ": " + entry);
        out.flush();
        out.close();
    }

}
