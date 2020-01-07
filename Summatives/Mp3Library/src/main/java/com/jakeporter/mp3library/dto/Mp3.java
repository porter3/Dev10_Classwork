package com.jakeporter.mp3library.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author jake
 */
public class Mp3{

    private String title; // read-only
    private LocalDate releaseDateLd;
    private String album;
    private String artist;
    private String genre;
    private String note;
    
    public Mp3(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getReleaseDateLd(){
        return releaseDateLd;
    }
    
    public String getReleaseDateStr() {
        String releaseDateLdStr = releaseDateLd.toString();
        return releaseDateLdStr;
    }

    public void setReleaseDateLd(String releaseDateLd) {
        // create LocalDate object with incoming string that uses the pattern yyyy-dd-mm
        LocalDate ld = LocalDate.parse(releaseDateLd, DateTimeFormatter.ofPattern("yyyy-dd-MM"));
        this.releaseDateLd = ld;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.title);
        hash = 31 * hash + Objects.hashCode(this.releaseDateLd);
        hash = 31 * hash + Objects.hashCode(this.album);
        hash = 31 * hash + Objects.hashCode(this.artist);
        hash = 31 * hash + Objects.hashCode(this.genre);
        hash = 31 * hash + Objects.hashCode(this.note);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mp3 other = (Mp3) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.album, other.album)) {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist)) {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
        if (!Objects.equals(this.releaseDateLd, other.releaseDateLd)) {
            return false;
        }
        return true;
    }

    
}
