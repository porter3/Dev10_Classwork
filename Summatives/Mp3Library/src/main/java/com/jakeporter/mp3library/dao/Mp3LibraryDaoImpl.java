package com.jakeporter.mp3library.dao;

import com.jakeporter.mp3library.dto.Mp3;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 *
 * @author jake
 */
public class Mp3LibraryDaoImpl implements Mp3LibraryDao{
    
    HashMap<String, Mp3> mp3Library = new HashMap();
    public static final String MP3_COLLECTION_FILE = "mp3Library.txt";
    public static final String DELIMITER = "::";

    @Override
    public Mp3 addMp3(Mp3 mp3Info) throws Mp3LibraryPersistenceException{
        // load mp3 library into memory
        loadMp3Library();
        Mp3 editedMp3 = assignToBlankFields(mp3Info);
        // add mp3 to map, assign it to this method's return value
        Mp3 mp3Created = mp3Library.put(editedMp3.getTitle(), editedMp3);
        // write updated library to persistent storage
        writeMp3Library();
        return mp3Created;
    }
    
    @Override
    public Mp3 removeMp3(String title) throws Mp3LibraryPersistenceException{
        // load mp3 library into memory
        loadMp3Library();
        // remove mp3 from library, assign it to this method's return value
        Mp3 removedMp3 = mp3Library.remove(title);
        // write updated library to persistent storage
        writeMp3Library();
        return removedMp3;
    }
    
    @Override
    public Mp3 findMp3ByTitle(String title) throws Mp3LibraryPersistenceException{
        // load mp3 library into memory
        loadMp3Library();
        return mp3Library.get(title);
    }
    
    @Override
    public List<Mp3> getAllMp3s() throws Mp3LibraryPersistenceException{
        // load mp3 library into memory
        loadMp3Library();
        return new ArrayList<>(mp3Library.values());
    }
    
    @Override
    public void editMp3Info(Mp3 mp3Info) throws Mp3LibraryPersistenceException{
        // load mp3 library into memory
        loadMp3Library();
        String trackTitle = mp3Info.getTitle();
        // assign strings to any blank fields
        mp3Info = assignToBlankFields(mp3Info);
        // accessing the map, reset each field with new track data
        mp3Library.get(trackTitle).setReleaseDateLd(mp3Info.getReleaseDateStr());
        mp3Library.get(trackTitle).setAlbum(mp3Info.getAlbum());
        mp3Library.get(trackTitle).setArtist(mp3Info.getArtist());
        mp3Library.get(trackTitle).setGenre(mp3Info.getGenre());
        mp3Library.get(trackTitle).setNote(mp3Info.getNote());
        // write updated library to persistent storage
        writeMp3Library();
    }
    
    @Override
    public String marshallMp3(Mp3 mp3ToConvert){
        // convert mp3 object to string
        return mp3ToConvert.getTitle() + DELIMITER 
                + mp3ToConvert.getReleaseDateStr() + DELIMITER
                + mp3ToConvert.getAlbum() + DELIMITER 
                + mp3ToConvert.getArtist() + DELIMITER 
                + mp3ToConvert.getGenre() + DELIMITER 
                + mp3ToConvert.getNote();
    }
    
    @Override
    public Mp3 unmarshallMp3(String mp3AsText){
        // create string array to hold values from string file, split at delimiter
        String[] mp3InfoFields = mp3AsText.split(DELIMITER);
        // create new Mp3 object and set fields
        Mp3 newMp3 = new Mp3(mp3InfoFields[0]);
        newMp3.setReleaseDateLd(mp3InfoFields[1]);
        newMp3.setAlbum(mp3InfoFields[2]);
        newMp3.setArtist(mp3InfoFields[3]);
        newMp3.setGenre(mp3InfoFields[4]);
        newMp3.setNote(mp3InfoFields[5]);
        return newMp3;
    }
    
    @Override
    public void loadMp3Library() throws Mp3LibraryPersistenceException{
        Scanner scanner;
        
        // create scanner to write to file if it exists
        try{
            // instantiate scanner object for writing to file is it exists
            scanner = new Scanner(new BufferedReader(new FileReader(MP3_COLLECTION_FILE)));
        }
        // throw application-specfic exception if file not found
        catch (FileNotFoundException e){
            throw new Mp3LibraryPersistenceException("Could not load address book into memory", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // creating reference to point to the new Mp3 object when it's created
        Mp3 loadedMp3;
        // do the following for all lines in the file
        while (scanner.hasNextLine()){
            // get next line in file
            currentLine = scanner.nextLine();
            // unmarshall it into an Mp3 object
            loadedMp3 = unmarshallMp3(currentLine);
            // load mp3 into map with title as key
            mp3Library.put(loadedMp3.getTitle(), loadedMp3);
        }
        // close the scanner to avoid memory leak
        scanner.close();
    }
    
    @Override
    public void writeMp3Library() throws Mp3LibraryPersistenceException{
        // create PrintWriter reference to write to text file
        PrintWriter out;
        
        // create PrintWriter object if file exists
        try{
            out = new PrintWriter(new FileWriter(MP3_COLLECTION_FILE));
        }
        catch(IOException e){
            throw new Mp3LibraryPersistenceException("Could not save library data", e);
        }
        // create String reference for assigning marshalled Mp3 data to
        String mp3AsText;
        // load mp3 library into list
        List<Mp3> mp3List = getAllMp3s();
        // iterate over each mp3 and marshall it into a String
        for (Mp3 unmarshalledMp3 : mp3List){
            mp3AsText = marshallMp3(unmarshalledMp3);
            // write mp3 String to file
            out.println(mp3AsText);
            // force PrintWriter to write remaining line to file
            out.flush();
        }
        // close PrintWriter to avoid memory leak
        out.close();
    }
    
    private Mp3 assignToBlankFields(Mp3 mp3){
        if (mp3.getReleaseDateStr() == null){
            LocalDate now = LocalDate.now();
            String nowStr = now.toString();
            mp3.setReleaseDateLd(nowStr);
        }
        if (mp3.getAlbum().isBlank() || mp3.getAlbum().isEmpty()){
            mp3.setAlbum("No album");
        }
        if (mp3.getArtist().isBlank() || mp3.getArtist().isEmpty()){
            mp3.setArtist("No artist");
        }
        if (mp3.getGenre().isBlank() || mp3.getGenre().isEmpty()){
            mp3.setGenre("No genre");
        }
        if (mp3.getNote().isBlank() || mp3.getNote().isEmpty()){
            mp3.setNote("No notes");
        }
        return mp3;
    }
    
    @Override
    public List<Mp3> getAllMp3sReleasedInLastNYears(long yearsPast) throws Mp3LibraryPersistenceException{
        // get stream of Mp3s
        loadMp3Library();
        return mp3Library.values()
                .stream()
                // logic: if it's true that the mp3 was released after the year specified...
                .filter(mp3 -> mp3.getReleaseDateLd().isAfter(LocalDate.now().minusYears(yearsPast)))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Mp3> getAllMp3sInGivenGenre(String genre) throws Mp3LibraryPersistenceException{
        loadMp3Library();
        return mp3Library.values()
                .stream()
                .filter(mp3 -> mp3.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}
