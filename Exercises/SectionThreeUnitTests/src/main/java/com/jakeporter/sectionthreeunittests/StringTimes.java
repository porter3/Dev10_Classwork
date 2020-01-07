package com.jakeporter.sectionthreeunittests;

/**
 *
 * @author jake
 */


public class StringTimes {

    // Given a String and a non-negative int n, return a larger String 
    // that is n copies of the original String. 
    //
    // stringTimes("Hi", 2) -> "HiHi"
    // stringTimes("Hi", 3) -> "HiHiHi"
    // stringTimes("Hi", 1) -> "Hi"
    public String stringTimes(String str, int n) {
        String multipliedString = "";
        for (int i = 0; i < n; i++){
            multipliedString += str;
        }
        return multipliedString;
    }

}
