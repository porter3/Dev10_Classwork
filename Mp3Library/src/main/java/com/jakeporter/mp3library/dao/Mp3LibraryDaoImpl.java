package com.jakeporter.mp3library.dao;

import com.jakeporter.mp3library.dto.Mp3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jake
 */
public class Mp3LibraryDaoImpl implements Mp3LibraryDao{
    
    HashMap<String, Mp3> mp3Library = new HashMap();
    public static final String MP3_COLLECTION_FILE = "mp3Library.txt";

    public Mp3 addMp3(Mp3 mp3Info){
        
        Mp3 mp3Created = mp3Library.put(mp3Info.getTitle(), mp3Info);
        return mp3Created;
    }
    
    public Mp3 removeMp3(String title){
        
        Mp3 removedMp3 = mp3Library.remove(title);
        return removedMp3;
    }
    
    public Mp3 findMp3ByTitle(String title){
        return mp3Library.get(title);
    }
    
    public List<Mp3> getAllMp3s(){
        return new ArrayList<Mp3>(mp3Library.values());
    }
    
    public void editMp3Info(Mp3 mp3Info){
        String trackTitle = mp3Info.getTitle();
        // accessing the maop, reset each field with new track data
        mp3Library.get(trackTitle).setReleaseDate(mp3Info.getReleaseDate());
        mp3Library.get(trackTitle).setAlbum(mp3Info.getAlbum());
        mp3Library.get(trackTitle).setArtist(mp3Info.getArtist());
        mp3Library.get(trackTitle).setGenre(mp3Info.getGenre());
        mp3Library.get(trackTitle).setNote(mp3Info.getNote());
    }
}
