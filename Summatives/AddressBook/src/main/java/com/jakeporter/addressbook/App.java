package com.jakeporter.addressbook;

import com.jakeporter.addressbook.controller.AddressBookController;
import com.jakeporter.addressbook.dao.AddressBookDao;
import com.jakeporter.addressbook.dao.AddressBookDaoImpl;
import com.jakeporter.addressbook.ui.AddressBookView;
import com.jakeporter.addressbook.ui.UserIO;
import com.jakeporter.addressbook.ui.UserIOConsoleImpl;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        UserIO io = new UserIOConsoleImpl();
        AddressBookView view = new AddressBookView(io);
        AddressBookDao dao = new AddressBookDaoImpl();
        AddressBookController controller = new AddressBookController(view, dao);
        controller.run();
    }
}
