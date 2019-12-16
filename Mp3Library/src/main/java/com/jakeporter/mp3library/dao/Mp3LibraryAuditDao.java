package com.jakeporter.mp3library.dao;

/**
 *
 * @author jake
 */
public interface Mp3LibraryAuditDao {

    /**
     * writes audit entry with timestamp
     * @param entry
     * @throws Mp3LibraryPersistenceException 
     */
    public void writeAuditEntry(String entry) throws Mp3LibraryPersistenceException;
}
