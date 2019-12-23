package com.jakeporter.vendingmachine.dao;

/**
 *
 * @author jake
 */
public interface VendingMachineAuditDao {

    public void writeAuditEntry(String entry) throws InventoryPersistenceException;
}
