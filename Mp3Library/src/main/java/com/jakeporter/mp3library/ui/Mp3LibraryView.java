package com.jakeporter.mp3library.ui;

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
        io.print("3. View Mp3");
        io.print("4. View Collection");
        io.print("5. Exit Program");
        
        return io.readInt("Please select one of the options above.", 1, 5);
    }
}
