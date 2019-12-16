package com.jakeporter.mp3library.service;

import com.jakeporter.mp3library.dao.Mp3LibraryAuditDao;
import com.jakeporter.mp3library.dao.Mp3LibraryAuditDaoFileImpl;
import com.jakeporter.mp3library.dao.Mp3LibraryDao;
import com.jakeporter.mp3library.dao.Mp3LibraryDaoImpl;
import com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException;
import com.jakeporter.mp3library.dto.Mp3;
import java.util.List;

/**
 *
 * @author jake
 */
public class Mp3LibraryServiceLayerImpl implements Mp3LibraryServiceLayer{
    
    Mp3LibraryAuditDao auditDao;
    Mp3LibraryDao crudDao;
    
    public Mp3LibraryServiceLayerImpl(Mp3LibraryAuditDao auditDao, Mp3LibraryDao crudDao){
        this.auditDao = auditDao;
        this.crudDao = crudDao;
    }

    @Override
    public void createMp3(Mp3 mp3) throws Mp3LibraryDataValidationException, Mp3LibraryPersistenceException {
        // validate title exists
        validateTitle(mp3);
        
        // persist the mp3
        crudDao.addMp3(mp3);
        
        // write to audit log
        auditDao.writeAuditEntry("Mp3 " + mp3.getTitle() + " CREATED");
    }

    @Override
    public List<Mp3> getAllMp3s() throws Mp3LibraryPersistenceException {
        return crudDao.getAllMp3s();
    }

    @Override
    public Mp3 getMp3(String title) throws Mp3LibraryPersistenceException {
        return crudDao.findMp3ByTitle(title);
    }

    @Override
    public Mp3 removeMp3(String title) throws Mp3LibraryPersistenceException {
        // remove Mp3 and return the Mp3
        Mp3 removedMp3 = crudDao.removeMp3(title);
        // write to audit file
        auditDao.writeAuditEntry("Mp3 " + removedMp3.getTitle() + " REMOVED");
        return removedMp3;
    }
    
    @Override
    public void editMp3(Mp3 mp3ToEdit) throws Mp3LibraryPersistenceException{
        crudDao.editMp3Info(mp3ToEdit);
    }

    private void validateTitle(Mp3 mp3) throws Mp3LibraryDataValidationException{
        // if user doesn't enter anything for title or just enters whitespace
        if (mp3.getTitle() == null || mp3.getTitle().trim().length() == 0){
            throw new Mp3LibraryDataValidationException("ERROR: Track must have a title");
        }
    }
}
