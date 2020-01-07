package com.jakeporter.mp3library.dao;

import com.jakeporter.mp3library.dto.Mp3;
import java.util.List;

/**
 *
 * @author jake
 */
public interface Mp3LibraryDao {
    
    /**
     * Adds mp3 to map of Mp3 objects with its title as the key
     * @param mp3Info mp3 object to be added
     * @return Mp3 object
     * @throws com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException
     */
    public Mp3 addMp3(Mp3 mp3Info) throws Mp3LibraryPersistenceException;
    
    /**
     * Removes mp3 associated with title from map of Mp3 objects
     * @param title of mp3
     * @return Mp3 that was removed
     * @throws com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException
     */
    public Mp3 removeMp3(String title) throws Mp3LibraryPersistenceException;
    
    /**
     * Edits an Mp3 in the data structure and writes map back to persistent file
     * @param mp3Info the mp3 being passed in to edit
     * @throws com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException
     */
    public void editMp3Info(Mp3 mp3Info) throws Mp3LibraryPersistenceException;
    
    /**
     * Gets and returns an mp3 object from the map
     * @param title of mp3
     * @return Mp3 object, null otherwise
     * @throws com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException
     */
    public Mp3 findMp3ByTitle(String title) throws Mp3LibraryPersistenceException;
    
    /**
     * Gets/returns a list of all Mp3 objects from the map
     * @return a list of all Mp3 objects in the map
     * @throws com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException
     */
    public List<Mp3> getAllMp3s() throws Mp3LibraryPersistenceException;
    
    /**
     * Translates an Mp3 object to a formatted String for storage
     * @param mp3Info Mp3 to be translated into a string
     * @return string that is formatted for writing to a file
     */
    public String marshallMp3(Mp3 mp3Info);
    
    /**
     * Translates a string of Mp3 object information into an Mp3 object
     * @param mp3AsString Delimited string for populating an Mp3 object
     * @return populated Mp3 object
     */
    public Mp3 unmarshallMp3(String mp3AsString);
    
    /**
     * Writes mp3 objects to permanent storage
     * @throws com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException
     */
    public void writeMp3Library() throws Mp3LibraryPersistenceException;
    
    /**
     * Reads mp3 objects into a data structure in memory
     * @throws com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException
     */
    public void loadMp3Library() throws Mp3LibraryPersistenceException;
    
    
    //NEW METHODS
    
    public List<Mp3> getAllMp3sReleasedInLastNYears(long yearsPast) throws Mp3LibraryPersistenceException;
    public List<Mp3> getAllMp3sInGivenGenre(String genre) throws Mp3LibraryPersistenceException;
}
