package dao;

/**
 *
 * @author jake
 */
public interface ClassRosterAuditDao {
    
    /**
     * @param entry
     * @throws ClassRosterPersistenceException 
     */
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;
}
