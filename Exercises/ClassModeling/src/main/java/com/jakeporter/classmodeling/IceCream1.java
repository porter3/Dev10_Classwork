package com.jakeporter.classmodeling;

/**
 *
 * @author jake
 */
public class IceCream1 {
    private String flavor;
    private static final boolean churned;
    private boolean hasNuts;
    
    //default is to say flavor does not have nuts
    public IceCream1(String flavor) {
        this.flavor = flavor;
        this.hasNuts = false;
    }

    public IceCream1(String flavor, boolean hasNuts) {
        this.flavor = flavor;
        this.churned = churned;
        this.hasNuts = hasNuts;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public boolean isChurned() {
        return churned;
    }

    public boolean isHasNuts() {
        return hasNuts;
    }

    public void setHasNuts(boolean hasNuts) {
        this.hasNuts = hasNuts;
    }

    public void addNuts(){
        this.hasNuts = true;
    }
}
