package com.jakeporter.datetimecodealong;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author jake
 */
public class App {
    public static void main(String[] args){
        
        LocalDate ld = LocalDate.now();
        System.out.println(ld);
        
        ld = LocalDate.parse("2015-01-01");
        System.out.println(ld);
        
        ld = LocalDate.parse("03/10/1995", DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(ld);
        
        String isoDate = ld.toString();
        System.out.println(isoDate);
        ld = LocalDate.parse(isoDate);
        System.out.println(ld);
        
        // take the LocalDate stored as ISO and change it to mm/dd/yyyy
        String formatted = ld.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println(formatted);
        
        formatted = ld.format(DateTimeFormatter.ofPattern("MM=dd=yyyy++++++++++"));
        System.out.println(formatted);
        
        // localized format
        formatted = ld.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        System.out.println(formatted);
    }
}
