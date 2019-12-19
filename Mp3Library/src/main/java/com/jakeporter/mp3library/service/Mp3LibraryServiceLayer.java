package com.jakeporter.mp3library.service;

import com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException;
import com.jakeporter.mp3library.dto.Mp3;
import java.util.List;

/**
 *
 * @author jake
 */
public interface Mp3LibraryServiceLayer {

    public void createMp3(Mp3 mp3) throws Mp3LibraryDataValidationException, Mp3LibraryPersistenceException;
    
    public List<Mp3> getAllMp3s() throws Mp3LibraryPersistenceException;
    
    public Mp3 getMp3(String title) throws Mp3LibraryPersistenceException;
    
    public Mp3 removeMp3(String title) throws Mp3LibraryPersistenceException;
    
    public void editMp3(Mp3 mp3ToEdit) throws Mp3LibraryPersistenceException;
    
    public List<Mp3> getAllMp3sReleasedInLastNYears(long pastYears) throws Mp3LibraryPersistenceException;

}