package com.jakeporter.mp3library.ui;

import com.jakeporter.mp3library.dto.Mp3;
import java.util.List;
import java.math.BigDecimal;

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
        io.print("\nMain Menu");
        io.print("1. Add new Mp3");
        io.print("2. Delete Mp3");
        io.print("3. Edit Mp3 Information");
        io.print("4. View Mp3");
        io.print("5. View Collection");
        io.print("6. View All Mp3s Released After Specific Date");
        io.print("7. View All Mp3s for Specific Genre");
        io.print("8. View All Mp3s for Specific Artist");
        io.print("9. View all Mp3s for Specific Album");
        io.print("10. View Average Age for All Mp3s");
        io.print("11. View Newest Mp3 in Library");
        io.print("12. View Oldest Mp3 in Library");
        io.print("13. View Average Number of Notes in Mp3 Collection");
        io.print("14. Exit Program");
        
        return io.readInt("Please select one of the options above.", 1, 14);
    }
    
    public boolean promptToContinue(){
        String choiceToContinue = io.readString("Would you like to do this again? (y/n)");
        while (true){
            switch(choiceToContinue.toLowerCase()){
                case "y":
                    return true;
                case "n":
                    return false;
                // repeat loop if user input is invalid
                default:
            }
        }
            
    }
    
    public Mp3 getNewMp3Info(){
        String title = io.readString("Please enter title");
        String releaseDate = io.readString("Please enter release date(yyyy-dd-MM)");
        // convert releaseDate string to LocalDate
        String album = io.readString("Please enter album title");
        String artist = io.readString("Please enter track artist");
        String genre = io.readString("Please enter genre");
        String note = io.readString("Please enter any notes you would like to record about the track");
        Mp3 currentMp3 = new Mp3(title);
        currentMp3.setReleaseDateLd(releaseDate);
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
        currentMp3.setReleaseDateLd(releaseDate);
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
            io.print("Title: " + mp3Info.getTitle()
                    + "\nRelease Date (LD): " + mp3Info.getReleaseDateStr()
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
            io.print("Title: " + mp3.getTitle()
                    + "\nRelease Date LD: " + mp3.getReleaseDateStr()
            + "\nAlbum: " + mp3.getAlbum() + "\nArtist: " + mp3.getArtist() + "\nGenre: " 
            + mp3.getGenre() + "\nNotes: " + mp3.getNote() + "\n");
        }
    }
    
    public void displayCreateMp3Banner(){
        io.print("ADD NEW TRACK");
    }
    
    public void displayCreateSuccessBanner(){
        io.print("Track successfully created");
    }
    
    public void displayDeleteMp3Banner(){
        io.print("DELETE TRACK");
    }
    
    public void displayDeletionSuccessBanner(){
        io.print("Track successfully deleted");
    }
    
    public void displayEditMp3Banner(){
        io.print("EDIT TRACK INFORMATION");
    }
    
    public void displayEditSuccessBanner(){
        io.print("Edit successful");
    }
    
    public void displayNonexistentMp3(){
        io.print("Track does not exist");
    }
    
    public void displayViewMp3Banner(){
        io.print("VIEW TRACK INFORMATION");
    }
    
    public void displayViewAllBanner(){
        io.print("VIEW ALL TRACKS");
    }
    
    public void displayExitBanner(){
        io.print("Goodbye");
    }
    
    public void unknownCommandBanner(){
        io.print("Command not recognized");
    }
    
    public void displayErrorMessage(String message){
        io.print(message);
    }
    
    public void displayViewAllMp3sReleasedPastNYears(){
        io.print("VIEW ALL MP3s RELEASED SINCE A SPECIFIC DATE");
    }
    
    public long getYearToViewMp3sSince(){
        return io.readLong("Please enter how many years back for which you would like to view tracks released since:", 0, 2000);
    }
    
    public void displayMp3sFromList(List<Mp3> mp3List){
        mp3List.stream()
                .forEach(mp3 -> {System.out.println("===================");
                                System.out.println(mp3.getTitle());       
                });
    }
   
    public void displayViewAllMp3sOfGivenGenre(){
        io.print("VIEW MP3S OF GIVEN GENRE");
    }
    
    public String getGenreForListViewing(){
        return io.readString("Please enter the genre you wish to view all tracks for:");
    }
    
    public void displayViewAllMp3sOfGivenArtist(){
        io.print("VIEW MP3S OF GIVEN ARTIST");
    }
    
    public String getArtistForListViewing(){
        return io.readString("Please enter the artist you wish to view all tracks for:");
    }
    
    public void displayViewByAlbumBanner(){
        io.print("VIEW MP3S OF GIVEN ALBUM");   
    }
    
    public String getAlbumForListViewing(){
        return io.readString("Please enter the album you wish to view all tracks for:");
    }
    
    public void displayViewAverageAgeBanner(){
        io.print("VIEW AVERAGE AGE OF TRACKS IN LIBRARY");
    }
    
    public void displayAverageAge(BigDecimal averageAge){
        io.print("The average age of your tracks is: " + averageAge + " years");
    }
    
    public void displayNewestMp3Banner(){
        io.print("VIEW MOST RECENTLY RELEASED TRACK");
    }
    
    public void displayNewestMp3(Mp3 newestMp3){
        io.print("The most recently released track you have is " + newestMp3.getTitle());
    }
    
    public void displayOldestMp3Banner(){
        io.print("VIEW OLDEST TRACK");
    }
    
    public void displayOldestMp3(Mp3 oldestMp3){
        io.print("The oldest track you have is " + oldestMp3.getTitle());
    }
    
    public void displayViewAverageNotesBanner(){
        io.print("VIEW AVERAGE NUMBER OF NOTES FOR TRACKS IN LIBRARY");
    }
    
    public void displayAverageNumberOfNotes(BigDecimal averageNotes){
        io.print(averageNotes + "% of your tracks contain notes");
    }
}
