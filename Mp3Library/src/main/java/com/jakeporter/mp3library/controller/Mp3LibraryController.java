package com.jakeporter.mp3library.controller;

import com.jakeporter.mp3library.dao.Mp3LibraryDao;
import com.jakeporter.mp3library.dto.Mp3;
import com.jakeporter.mp3library.ui.Mp3LibraryView;
import java.util.List;

/**
 *
 * @author jake
 */
public class Mp3LibraryController {
    
    Mp3LibraryView view;
    Mp3LibraryDao dao;
    
    public Mp3LibraryController(Mp3LibraryView view, Mp3LibraryDao dao){
        this.view = view;
        this.dao = dao;
    }
    

    public void run(){
                
        int menuSelection = 0;
        boolean programRunning = true;
        
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
                case 6: // exit program
                    programRunning = false;
                    // print exit statement
                    break;
                default:
                    //print 'command not recognized'
            }
        }
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
    
    private void createMp3(){
        view.displayCreateMp3Banner();
        // get new mp3 info from user
        Mp3 newMp3 = view.getNewMp3Info();
        // add mp3 to map and write map to persistent storage
        dao.addMp3(newMp3);
        view.displayCreateSuccessBanner();
    }
    
    private void editMp3(){
        // display banner
        view.displayEditMp3Banner();
        // get title of mp3 to edit
        String title = view.getTitleForEditing();
        // get mp3 for editing
        Mp3 mp3ToEdit = dao.findMp3ByTitle(title);
        //if track exists:
        if (mp3ToEdit != null){
            // prompt user to edit mp3 by requesting new information
            Mp3 newMp3Info = view.getMp3Edits(title);
            // write new information to map
            dao.editMp3Info(newMp3Info);
            // display success banner
            view.displayEditSuccessBanner();
        }
        else{
        // else display nonexsitent track banner
        view.displayNonexistentMp3();
        }
    }
    
    private void viewMp3Info(){
        view.displayViewMp3Banner();
        String trackTitle = view.getTitleForViewing();
        Mp3 mp3Info = dao.findMp3ByTitle(trackTitle);
        view.displayMp3Info(mp3Info);
    }
    
    private void listAllMp3s(){
        view.displayViewAllBanner();
        List<Mp3> mp3Collection = dao.getAllMp3s();
        view.displayMp3Collection(mp3Collection);
    }
    
    private void deleteMp3(){
        // display banner
        view.displayDeleteMp3Banner();
        // get title of Mp3 to delete (pass into DAO method)
        String title = view.getTitleForDeletion();
        // remove Mp3 from collection / re-write map to persistent storage (return Mp3)
        Mp3 deletedMp3 = dao.removeMp3(title);
        // if Mp3 is null, tell user it doesn't exist
        if (deletedMp3 == null){
            view.displayNonexistentMp3();
        }
        else{
            view.displayDeletionSuccessBanner();
        }
    }
}
