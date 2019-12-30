package com.jakeporter.flooringmastery.service;

import com.jakeporter.flooringmastery.dao.FlooringAuditDao;
import com.jakeporter.flooringmastery.dao.FlooringDao;

/**
 *
 * @author jake
 */
public class FlooringServiceLayerImpl {

    private FlooringDao crudDao;
    private FlooringAuditDao auditDao;
    
    FlooringServiceLayerImpl(FlooringDao crudDao, FlooringAuditDao auditDao){
        this.crudDao = crudDao;
        this.auditDao = auditDao;
    }
}
