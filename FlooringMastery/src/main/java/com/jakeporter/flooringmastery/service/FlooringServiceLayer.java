package com.jakeporter.flooringmastery.service;

/**
 *
 * @author jake
 */
public interface FlooringServiceLayer {

    public int getHighestOrderNumber();
    public int generateOrderNumber(int highestOrderNumber);
}
