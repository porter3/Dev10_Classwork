package com.jakeporter.mp3library.controller;

import com.jakeporter.mp3library.dao.Mp3LibraryDao;
import com.jakeporter.mp3library.ui.Mp3LibraryView;

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
            
            //allow user to select menu option
            menuSelection = getMenuSelection();
        }
    }
    
    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }
}
