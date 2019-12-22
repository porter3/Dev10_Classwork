package com.jakeporter.vendingmachine.service;

import com.jakeporter.vendingmachine.dao.InventoryPersistenceException;
import com.jakeporter.vendingmachine.dao.VendingMachineAuditDao;
import com.jakeporter.vendingmachine.dao.VendingMachineDao;
import com.jakeporter.vendingmachine.dto.Change;
import com.jakeporter.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jake
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    
    VendingMachineDao crudDao;
    VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao crudDao, VendingMachineAuditDao auditDao){
        this.crudDao = crudDao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getInventory() throws InventoryPersistenceException{
        return crudDao.getInventory();
    }
    
    public Item getItem(String itemName) throws InventoryPersistenceException{
        return crudDao.getItem(itemName);
    }

    @Override
    public void vendItem(Item item) throws InventoryPersistenceException{
        crudDao.loadInventory();
        crudDao.updateInventory(item);
        crudDao.writeInventory();
    }

    // REFACTOR
    @Override
    // method takens an argument of the user's money with scale of 2, converts to whole number
    // i.e. 2.50 -> 250
    public Change calculateUserChange(BigDecimal userMoney, Item itemVended) {
        BigDecimal moneyWholeNumber = userMoney.multiply(new BigDecimal("100"));
        BigDecimal userChange = moneyWholeNumber.subtract(itemVended.getCost());
        return new Change(userChange.toString());
    }
}
