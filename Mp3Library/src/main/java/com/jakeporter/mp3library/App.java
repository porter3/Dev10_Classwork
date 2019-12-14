package com.jakeporter.mp3library;

import com.jakeporter.mp3library.controller.Mp3LibraryController;
import com.jakeporter.mp3library.dao.Mp3LibraryDao;
import com.jakeporter.mp3library.dao.Mp3LibraryDaoImpl;
import com.jakeporter.mp3library.ui.Mp3LibraryView;
import com.jakeporter.mp3library.ui.UserIO;
import com.jakeporter.mp3library.ui.UserIOConsoleImpl;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        UserIO io = new UserIOConsoleImpl();
        Mp3LibraryView view = new Mp3LibraryView(io);
        Mp3LibraryDao dao = new Mp3LibraryDaoImpl();
        Mp3LibraryController controller = new Mp3LibraryController(view, dao);
        
        controller.run();
    }
}
