package com.jakeporter.sectionthreeunittests;

/**
 *
 * @author jake
 */
public class GreatParty {

    // When squirrels get together for a party, they like to have cigars.
// A squirrel party is successful when the number of cigars is between
// 40 and 60, inclusive. Unless it is the weekend, in which case there 
// is no upper bound on the number of cigars. Return true if the party
// with the given values is successful, or false otherwise.
//
    
    // Test cases
//greatParty(30, false) → false
//greatParty(50, false) → true
//greatParty(70, true) → true
//greatParty(39, true) → false
//greatParty(39, false) → false
//greatParty(40, true) → true
//greatParty(40, false) → true
//greatParty(60, true) → true
//greatParty(60, false) → true
//greatParty(61, true) → true
//greatParty(61, false) → false
public boolean greatParty(int cigars, boolean isWeekend) {
    if (isWeekend){
        if (cigars >= 40){
            return true;
        }
        else{
            return false;
        }
    }
    // not the weekend
    else{
        if (cigars >= 40 && cigars <=60){
            return true;
        }
        else{
            return false;
        }
    }
}

}
