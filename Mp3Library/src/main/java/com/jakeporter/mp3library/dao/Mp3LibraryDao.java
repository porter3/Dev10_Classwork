/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     * @param title of Mp3
     * @param Mp3 object
     * @return Mp3 object
     */
    public Mp3 addMp3(Mp3 mp3Info);
    
    /**
     * Removes mp3 associated with title from map of Mp3 objects
     * @param title of mp3
     * @return Mp3 that was removed
     */
    public Mp3 removeMp3(String title);
    
    /**
     * Edits an Mp3 in the data structure and writes map back to persistent file
     * @param Mp3 to edit
     * @return void
     */
    public void editMp3Info(Mp3 mp3Info);
    
    /**
     * Gets and returns an mp3 object from the map
     * @param title of mp3
     * @return Mp3 object, null otherwise
     */
    public Mp3 findMp3ByTitle(String title);
    
    /**
     * Gets/returns a list of all Mp3 objects from the map
     * @return a list of all Mp3 objects in the map
     */
    public List<Mp3> getAllMp3s();
    
//    /**
//     * Translates an Mp3 object to a formatted String for storage
//     * @param Mp3 to be translated into a string
//     * @return string that is formatted for writing to a file
//     */
//    public String marshallMp3(Mp3 mp3Info);
//    
//    /**
//     * Translates a string of Mp3 object information into an Mp3 object
//     * @param Delimited string for populating an Mp3 object
//     * @return populated Mp3 object
//     */
//    public Mp3 unmarshallMp3(String mp3AsString);
//    
//    
//    /**
//     * Writes mp3 objects to permanent storage
//     */
//    public void writeMp3Collection();
//    
//    /**
//     * Reads mp3 objects into a data structure in memory
//     */
//    public void loadMp3Collection();
}
