package com.jakeporter.mp3library.service;

import com.jakeporter.mp3library.dao.Mp3LibraryAuditDao;
import com.jakeporter.mp3library.dao.Mp3LibraryAuditDaoFileImpl;
import com.jakeporter.mp3library.dao.Mp3LibraryDao;
import com.jakeporter.mp3library.dao.Mp3LibraryDaoImpl;
import com.jakeporter.mp3library.dao.Mp3LibraryPersistenceException;
import com.jakeporter.mp3library.dto.Mp3;
import java.math.BigDecimal;
import java.math.MathContext;
import static java.math.RoundingMode.HALF_UP;
import java.util.List;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import static java.util.concurrent.TimeUnit.DAYS;
import java.util.stream.Collectors;

/**
 *
 * @author jake
 */
public class Mp3LibraryServiceLayerImpl implements Mp3LibraryServiceLayer{
    
    Mp3LibraryAuditDao auditDao;
    Mp3LibraryDao crudDao;
    
    public Mp3LibraryServiceLayerImpl(Mp3LibraryAuditDao auditDao, Mp3LibraryDao crudDao){
        this.auditDao = auditDao;
        this.crudDao = crudDao;
    }

    @Override
    public void createMp3(Mp3 mp3) throws Mp3LibraryDataValidationException, Mp3LibraryPersistenceException {
        // validate title exists
        validateTitle(mp3);
        
        // persist the mp3
        crudDao.addMp3(mp3);
        
        // write to audit log
        auditDao.writeAuditEntry("Mp3 " + mp3.getTitle() + " CREATED");
    }

    @Override
    public List<Mp3> getAllMp3s() throws Mp3LibraryPersistenceException {
        return crudDao.getAllMp3s();
    }

    @Override
    public Mp3 getMp3(String title) throws Mp3LibraryPersistenceException {
        return crudDao.findMp3ByTitle(title);
    }

    @Override
    public Mp3 removeMp3(String title) throws Mp3LibraryPersistenceException {
        // remove Mp3 and return the Mp3
        Mp3 removedMp3 = crudDao.removeMp3(title);
        // write to audit file
        auditDao.writeAuditEntry("Mp3 " + removedMp3.getTitle() + " REMOVED");
        return removedMp3;
    }
    
    @Override
    public void editMp3(Mp3 mp3ToEdit) throws Mp3LibraryPersistenceException{
        crudDao.editMp3Info(mp3ToEdit);
    }

    private void validateTitle(Mp3 mp3) throws Mp3LibraryDataValidationException{
        // if user doesn't enter anything for title or just enters whitespace
        if (mp3.getTitle() == null || mp3.getTitle().trim().length() == 0){
            throw new Mp3LibraryDataValidationException("ERROR: Track must have a title");
        }
    }
    
    
    // NEW METHODS
    
    @Override
    public List<Mp3> getAllMp3sReleasedInLastNYears(long pastYears) throws Mp3LibraryPersistenceException{
        return crudDao.getAllMp3sReleasedInLastNYears(pastYears);
    }
    
    @Override
    public List<Mp3> getAllMp3sInGivenGenre(String genre) throws Mp3LibraryPersistenceException{
        return crudDao.getAllMp3sInGivenGenre(genre);
    }
    
    @Override
    public List<Mp3> getAllMp3sByGivenArtist(String artist) throws Mp3LibraryPersistenceException{
        List<Mp3> library = crudDao.getAllMp3s();
        return library.stream()
                .filter(l -> l.getArtist().equalsIgnoreCase(artist))
                .collect(Collectors.toList());
    }

    @Override
    public List<Mp3> getAllMp3sInGivenAlbum(String album) throws Mp3LibraryPersistenceException{
        List<Mp3> library = crudDao.getAllMp3s();
        return library.stream()
                .filter(l -> l.getAlbum().equalsIgnoreCase(album))
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getAverageAgeOfMp3s() throws Mp3LibraryPersistenceException{
        List<Mp3> library = crudDao.getAllMp3s();
        Double averageAge = library.stream()
                .mapToDouble(l -> // extract period in days between release date and now
                        Period.between(l.getReleaseDateLd(), LocalDate.now()).getYears())
                .average()
                .getAsDouble();
        BigDecimal averageAgeBd = new BigDecimal(averageAge.toString());
        return averageAgeBd.setScale(2, HALF_UP);
    }
    
    @Override
    public Mp3 getNewestMp3() throws Mp3LibraryPersistenceException{
        List<Mp3> library = crudDao.getAllMp3s();
        return library.stream()
                .max(new Mp3Comparator())
                .get();
    }

    @Override
    public Mp3 getOldestMp3() throws Mp3LibraryPersistenceException {
        List<Mp3> library = crudDao.getAllMp3s();
        return library.stream()
                .min(new Mp3Comparator())
                .get();
    }

    @Override
    public BigDecimal getAverageNumberOfNotes() throws Mp3LibraryPersistenceException {
        List<Mp3> library = crudDao.getAllMp3s();
        Long totalMp3s = library.stream()
                .count();
        Long mp3sWithNotes = library.stream()
                .filter(l -> l.getNote().equals("No notes"))
                .count();
        BigDecimal totalMp3sBd = new BigDecimal(totalMp3s.toString());
        BigDecimal mp3sWithNotesBd = new BigDecimal(mp3sWithNotes.toString());
        return mp3sWithNotesBd.divide(totalMp3sBd, 4, HALF_UP).multiply(new BigDecimal("100")).setScale(2);
    }
}
