package com.jakeporter.sectionthreeunittests;

/**
 *
 * @author jake
 */
public class CountXX {
    // Count the number of "xx" in the given String. We'll say 
    // that overlapping is allowed, so "xxx" contains 2 "xx".  
    //
    // countXX("abcxx") -> 1
    // countXX("xxx") -> 2
    // countXX("xxxx") -> 3
    public int countXX(String str) {
        int xxCounter = 0;
        // iterate over each char in string
        for (int i = 0; i < str.length()-1; i++){
            if ((str.charAt(i) == 'x') && (str.charAt(i+1) == 'x')){
                xxCounter++;
            }
        }
        return xxCounter;
    }
}
