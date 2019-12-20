/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jakeporter.mp3library.dao;

import com.jakeporter.mp3library.dto.Mp3;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author jake
 */
public class Mp3LibraryDaoTest {
    
    Mp3LibraryDao dao = new Mp3LibraryDaoImpl();
    
    public Mp3LibraryDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        // remove everything from memory
        // get all mp3s
        List<Mp3> mp3List = dao.getAllMp3s();
        // iterate over list, remove each mp3 from memory by accessing title
        for (Mp3 mp3 : mp3List){
            dao.removeMp3(mp3.getTitle());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMp3 and getMp3 methods, of class Mp3LibraryDao.
     */
    @Test
    public void testAddGetMp3() throws Exception {
        // create bogus mp3 info
        Mp3 mp31 = new Mp3("Title1");
        mp31.setReleaseDateLd("2019-05-05");
        mp31.setAlbum("Album1");
        mp31.setGenre("rap");
        mp31.setArtist("Artist1");
        mp31.setNote("random note");
        dao.addMp3(mp31);
        
        // assert that first mp3 was added
        Mp3 fromDao = dao.findMp3ByTitle(mp31.getTitle());
        assertEquals(mp31, fromDao);
    }

    /**
     * Test of removeMp3 method, of class Mp3LibraryDao.
     */
    @Test
    public void testRemoveMp3() throws Exception {
        
        Mp3 mp32 = new Mp3("Title2");
        mp32.setReleaseDateLd("2019-03-10");
        mp32.setAlbum("Album2");
        mp32.setGenre("rap");
        mp32.setArtist("Artist2");
        mp32.setNote("random note");
        dao.addMp3(mp32);
                
        Mp3 mp31 = new Mp3("Title1");
        mp31.setReleaseDateLd("2019-05-05");
        mp31.setAlbum("Album1");
        mp31.setGenre("rap");
        mp31.setArtist("Artist1");
        mp31.setNote("random note");
        dao.addMp3(mp31);
        
        assertEquals(2, dao.getAllMp3s().size());
        
        dao.removeMp3("Title2");
        assertEquals(1, dao.getAllMp3s().size());
        assertNull(dao.findMp3ByTitle("Title2"));
    }

    /**
     * Test of editMp3Info method, of class Mp3LibraryDao.
     */
    @Test
    public void testEditGetMp3Info() throws Exception {
        Mp3 mp31 = new Mp3("Title1");
        mp31.setReleaseDateLd("2019-01-01");
        mp31.setAlbum("Album1");
        mp31.setGenre("rap");
        mp31.setArtist("Artist1");
        mp31.setNote("random note");
        dao.addMp3(mp31);
                
        Mp3 mp32 = new Mp3("Title1");
        mp32.setReleaseDateLd("1999-01-01");
        mp32.setAlbum("ALBUM2");
        mp32.setGenre("ROCK");
        mp32.setArtist("ARTIST2");
        mp32.setNote("boooo notes");
        
        // 2nd mp3 has the same title: editMp3Info() will think it's the new user-entered editing info for the first mp3
        dao.editMp3Info(mp32);
        // access the Mp3 object in the data structure
        assertEquals(dao.findMp3ByTitle(mp31.getTitle()), mp32);
    }

    /**
     * Test of getAllMp3s method, of class Mp3LibraryDao.
     */
    @Test
    public void testGetAllMp3s() throws Exception {
        assertEquals(0, dao.getAllMp3s().size());
        
        Mp3 mp32 = new Mp3("Title2");
        mp32.setReleaseDateLd("2019-01-01");
        mp32.setAlbum("Album2");
        mp32.setGenre("rap");
        mp32.setArtist("Artist2");
        mp32.setNote("random note");
        dao.addMp3(mp32);
        
        Mp3 mp31 = new Mp3("Title1");
        mp31.setReleaseDateLd("2019-01-01");
        mp31.setAlbum("Album1");
        mp31.setGenre("rap");
        mp31.setArtist("Artist1");
        mp31.setNote("random note");
        dao.addMp3(mp31);
        
        assertEquals(2, dao.getAllMp3s().size());
    }
    
    @Test
    public void testGetAllMp3sInPastNYears() throws Exception{
        // add two DAOs to map, different release date years
        
        // call dao to put them into a List
        
        // assert that the list contains one element
        
        // assert that the list contains the proper element
        // assert that the list contains one element
    }
}
