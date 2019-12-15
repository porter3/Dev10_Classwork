package com.jakeporter.mp3library.ui;

import com.jakeporter.mp3library.dto.Mp3;
import java.util.List;

/**
 *
 * @author jake
 */
public class Mp3LibraryView {

    UserIO io;
    
    public Mp3LibraryView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetSelection(){
        // print menu for user
        io.print("Main Menu");
        io.print("1. Add new Mp3");
        io.print("2. Delete Mp3");
        io.print("3. Edit Mp3 Information");
        io.print("4. View Mp3");
        io.print("5. View Collection");
        io.print("6. Exit Program");
        
        return io.readInt("Please select one of the options above.", 1, 6);
    }
    
    public Mp3 getNewMp3Info(){
        String title = io.readString("Please enter title");
        String releaseDate = io.readString("Please enter release date");
        String album = io.readString("Please enter album title");
        String artist = io.readString("Please enter track artist");
        String genre = io.readString("Please enter genre");
        String note = io.readString("Please enter any notes you would like to record about the track");
        Mp3 currentMp3 = new Mp3(title);
        currentMp3.setReleaseDate(releaseDate);
        currentMp3.setAlbum(album);
        currentMp3.setArtist(artist);
        currentMp3.setGenre(genre);
        currentMp3.setNote(note);
        return currentMp3;
    }
    
    public String getTitleForDeletion(){
        return io.readString("Please enter the track name to delete it");
    }
    
    public Mp3 getMp3Edits(String title){
        // get new info for mp3 track
        String releaseDate = io.readString("Please enter release date");
        String album = io.readString("Please enter album title");
        String artist = io.readString("Please enter track artist");
        String genre = io.readString("Please enter genre");
        String note = io.readString("Please enter any notes you would like to record about the track");
        Mp3 currentMp3 = new Mp3(title);
        // intialize all uninitialized fields of new mp3 object
        currentMp3.setReleaseDate(releaseDate);
        currentMp3.setAlbum(album);
        currentMp3.setArtist(artist);
        currentMp3.setGenre(genre);
        currentMp3.setNote(note);
        return currentMp3;
    }
    
    public String getTitleForEditing(){
        return io.readString("Please enter track name to edit information");
    }
    
    public void displayMp3Info(Mp3 mp3Info){
        if (mp3Info != null){
            io.print("Title: " + mp3Info.getTitle() + "\nRelease date: " + mp3Info.getReleaseDate()
            + "\nAlbum: " + mp3Info.getAlbum() + "\nArtist: " + mp3Info.getArtist() + "\nGenre: " 
            + mp3Info.getGenre() + "\nNotes: " + mp3Info.getNote() + "\n");
        }
        else{
            io.print("Mp3 does not exist\n");
        }
        io.readString("Hit enter to continue");
    }
    
    public String getTitleForViewing(){
        return io.readString("Please enter track name to view information");
    }
    
    public void displayMp3Collection(List<Mp3> mp3Library){
        for (Mp3 mp3 : mp3Library){
            io.print("Title: " + mp3.getTitle() + "\nRelease date: " + mp3.getReleaseDate()
            + "\nAlbum: " + mp3.getAlbum() + "\nArtist: " + mp3.getArtist() + "\nGenre: " 
            + mp3.getGenre() + "\nNotes: " + mp3.getNote() + "\n");
        }
    }
    
    public void displayCreateMp3Banner(){
        io.print("ADD NEW TRACK");
    }
    
    public void displayCreateSuccessBanner(){
        io.print("Track successfully created\n");
    }
    
    public void displayDeleteMp3Banner(){
        io.print("DELETE TRACK");
    }
    
    public void displayDeletionSuccessBanner(){
        io.print("Track successfully deleted\n");
    }
    
    public void displayEditMp3Banner(){
        io.print("EDIT TRACK INFORMATION");
    }
    
    public void displayEditSuccessBanner(){
        io.print("Edit successful\n");
    }
    
    public void displayNonexistentMp3(){
        io.print("Track does not exist\n");
    }
    
    public void displayViewMp3Banner(){
        io.print("VIEW TRACK INFORMATION");
    }
    
    public void displayViewAllBanner(){
        io.print("VIEW ALL TRACKS");
    }
}
