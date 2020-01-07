package com.jakeporter.mp3library.service;

import com.jakeporter.mp3library.dto.Mp3;
import java.util.Comparator;

/**
 *
 * @author jake
 */
public class Mp3Comparator implements Comparator<Mp3>{

    @Override
    public int compare(Mp3 o1, Mp3 o2) {
        return o1.getReleaseDateLd().compareTo(o2.getReleaseDateLd());
    }

}
