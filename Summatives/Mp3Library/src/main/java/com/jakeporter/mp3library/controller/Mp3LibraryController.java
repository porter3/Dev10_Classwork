package com.jakeporter.mp3library.controller;

import com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException;
import com.jakeporter.mp3library.dto.Mp3;
import com.jakeporter.mp3library.service.Mp3LibraryDataValidationException;
import com.jakeporter.mp3library.service.Mp3LibraryServiceLayer;
import com.jakeporter.mp3library.ui.Mp3LibraryView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jake
 * 
 * 
 * TODO: fix unit tests for DAO - implement tests for service layer, add exception handling for users entering wrong dateTime format
 */
public class Mp3LibraryController {
    
    Mp3LibraryView view;
    Mp3LibraryServiceLayer service;
    
    // allow for selection of various view and DAO objects in main()
    public Mp3LibraryController(Mp3LibraryView view, Mp3LibraryServiceLayer service){
        this.view = view;
        this.service = service;
    }
    

    public void run(){
                
        int menuSelection = 0;
        boolean programRunning = true;
        
        try{
            while(programRunning){

                // display menu and allow user to select function to run
                menuSelection = getMenuSelection();
                switch(menuSelection){
                    case 1:
                        createMp3();
                        break;
                    case 2: // deletion
                        deleteMp3();
                        break;
                    case 3: // editing
                        editMp3();
                        break;
                    case 4: // view
                        viewMp3Info();
                        break;
                    case 5: // view collection
                        listAllMp3s();
                        break;
                    case 6:
                        viewAllMp3sInPastNYears();
                        break;
                    case 7:
                        viewMp3sByGenre();
                        break;
                    case 8:
                        viewMp3sByArtist();
                        break;
                    case 9:
                        viewMp3sByAlbum();
                        break;
                    case 10:
                        viewAverageMp3Age();
                        break;
                    case 11:
                        viewNewestMp3();
                        break;
                    case 12:
                        viewOldestMp3();
                        break;
                    case 13:
                        viewAverageOfNoteCount();
                        break;
                    case 14: // exit program
                        programRunning = false;
                        break;
                    default: // unknown command
                        unknownCommand();
                }
            }
            exitMessage();
        }
        // if file is not found to write to/read from, print error message
        catch (Mp3LibraryPersistenceException | Mp3LibraryDataValidationException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createMp3() throws Mp3LibraryDataValidationException, Mp3LibraryPersistenceException{
        view.displayCreateMp3Banner();
        // initiate a "session" that allows user to repeat action if they choose
        boolean ongoingSession = true;
        while (ongoingSession){
            // get new mp3 info from user
            Mp3 newMp3 = view.getNewMp3Info();
            // add mp3 to map and write map to persistent storage
            service.createMp3(newMp3);
            view.displayCreateSuccessBanner();
            ongoingSession = view.promptToContinue();
        }
    }
    
    private void editMp3() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayEditMp3Banner();
        // get title of mp3 to edit
        String title = view.getTitleForEditing();
        // get mp3 for editing
        Mp3 mp3ToEdit = service.getMp3(title);
        //if track exists:
        if (mp3ToEdit != null){
            // prompt user to edit mp3 by requesting new information
            Mp3 newMp3Info = view.getMp3Edits(title);
            // write new information to map
            service.editMp3(newMp3Info);
            // display success banner
            view.displayEditSuccessBanner();
        }
        else{
        // else display nonexsitent track banner
        view.displayNonexistentMp3();
        }    
    }
    
    private void viewMp3Info() throws Mp3LibraryPersistenceException{
        view.displayViewMp3Banner();
        // initiate a "session" that allows user to repeat action if they choose
        boolean ongoingSession = true;
        while (ongoingSession){
            String trackTitle = view.getTitleForViewing();
            Mp3 mp3Info = service.getMp3(trackTitle);
            view.displayMp3Info(mp3Info);
            ongoingSession = view.promptToContinue();
        }
    }
    
    private void listAllMp3s() throws Mp3LibraryPersistenceException{
        view.displayViewAllBanner();
        List<Mp3> mp3Collection = service.getAllMp3s();
        view.displayMp3Collection(mp3Collection);
    }
    
    private void deleteMp3() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayDeleteMp3Banner();
        // initiate a "session" that allows user to repeat action if they choose
        boolean ongoingSession = true;
        while (ongoingSession){
            // get title of Mp3 to delete (pass into DAO method)
            String title = view.getTitleForDeletion();
            // remove Mp3 from collection / re-write map to persistent storage (return Mp3)
            Mp3 deletedMp3 = service.removeMp3(title);
            // if Mp3 is null, tell user it doesn't exist
            if (deletedMp3 == null){
                view.displayNonexistentMp3();
            }
            else{
                view.displayDeletionSuccessBanner();
            }
            // prompt user for their choice to continue performing the same action
            ongoingSession = view.promptToContinue();
        }
    }
    
    private void viewAllMp3sInPastNYears() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayViewAllMp3sReleasedPastNYears();
        // get user input for yearsPast
        long yearSince = view.getYearToViewMp3sSince();
        List<Mp3> mp3List = service.getAllMp3sReleasedInLastNYears(yearSince);
        // display list
        view.displayMp3sFromList(mp3List);
    }
    
    private void viewMp3sByGenre() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayViewAllMp3sOfGivenGenre();
        // get user input for genre
        String genreToView = view.getGenreForListViewing();
        // get list of all mp3s in that genre
        List<Mp3> mp3List = service.getAllMp3sInGivenGenre(genreToView);
        // display list
        view.displayMp3sFromList(mp3List);
    }
    
    private void viewMp3sByArtist() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayViewAllMp3sOfGivenArtist();
        // get input for artist
        String artistToView = view.getArtistForListViewing();
        // get list of mp3s by artist
        List<Mp3> mp3List = service.getAllMp3sByGivenArtist(artistToView);
        // display list
        view.displayMp3sFromList(mp3List);
    }
    
    private void viewMp3sByAlbum() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayViewByAlbumBanner();
        // get input for album
        String albumToView = view.getAlbumForListViewing();
        // get list of mp3s by album
        List<Mp3> mp3List = service.getAllMp3sInGivenAlbum(albumToView);
        // display list
        view.displayMp3sFromList(mp3List);
    }
    
    private void viewAverageMp3Age() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayViewAverageAgeBanner();
        // get average age of mp3s in library, return as int
        BigDecimal averageAge = service.getAverageAgeOfMp3s();
        // display age
        view.displayAverageAge(averageAge);
    }
    
    private void viewNewestMp3() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayNewestMp3Banner();
        // get newest mp3
        Mp3 newestMp3 = service.getNewestMp3();
        // display newest mp3
        view.displayNewestMp3(newestMp3);
    }
    
    private void viewOldestMp3() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayOldestMp3Banner();
        // get oldest mp3
        Mp3 oldestMp3 = service.getOldestMp3();
        // display oldest mp3
        view.displayOldestMp3(oldestMp3);
    }
    
    private void viewAverageOfNoteCount() throws Mp3LibraryPersistenceException{
        // display banner
        view.displayViewAverageNotesBanner();
        // get average number of notes in library
        BigDecimal averageNotes = service.getAverageNumberOfNotes();
        // display average number of notes
        view.displayAverageNumberOfNotes(averageNotes);
    }
    
    private void exitMessage(){
        view.displayExitBanner();
    }
    
    private void unknownCommand(){
        view.unknownCommandBanner();
    }
}
