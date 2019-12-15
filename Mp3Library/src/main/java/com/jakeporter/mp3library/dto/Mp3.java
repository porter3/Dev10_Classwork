package com.jakeporter.mp3library.dto;

/**
 *
 * @author jake
 */
public class Mp3 {

    private String title; // read-only
    private String releaseDate;
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
    
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    
    
    
}
