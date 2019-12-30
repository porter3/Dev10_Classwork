package com.jakeporter.flooringmastery.controller;

import com.jakeporter.flooringmastery.service.FlooringServiceLayer;
import com.jakeporter.flooringmastery.ui.FlooringView;

/**
 *
 * @author jake
 */
public class FlooringController {

    private FlooringView view;
    private FlooringServiceLayer service;
    
    FlooringController(FlooringView view, FlooringServiceLayer service){
        this.view = view;
        this.service = service;
    }
}
