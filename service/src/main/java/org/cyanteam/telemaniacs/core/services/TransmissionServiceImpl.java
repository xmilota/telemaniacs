package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.dao.TransmissionDao;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.entities.Voting;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Transmission service
 * @author Michael Le
 */
@Service
public class TransmissionServiceImpl implements TransmissionService {
    @Inject
    private TransmissionDao transmissionDao;

    @Override
    public void createTransmission(Transmission transmission) {
        transmissionDao.create(transmission);
    }

    @Override
    public void updateTransmission(Transmission transmission) {
        transmissionDao.update(transmission);
    }

    @Override
    public void removeTransmission(Transmission transmission) {
        transmissionDao.delete(transmission);
    }

    @Override
    public Transmission getTransmissionById(Long id) {
        return transmissionDao.findById(id);
    }

    @Override
    public Transmission getTransmissionByName(String name) {
        return transmissionDao.findByName(name);
    }

    @Override
    public List<Transmission> getTransmissionsByType(TransmissionType type) {
        return null;
    }

    @Override
    public void addOccurrence(TransmissionOccurrence occurrence) {

    }

    @Override
    public void updateOccurrence(TransmissionOccurrence occurrence) {

    }

    @Override
    public void removeOccurrence(TransmissionOccurrence occurrence) {

    }

    @Override
    public List<TransmissionOccurrence> getOccurrences(Transmission transmission) {
        return null;
    }

    @Override
    public List<TransmissionOccurrence> getUpcomingOccurrences(Transmission transmission) {
        return null;
    }

    @Override
    public List<Voting> getVotings(Transmission transmission) {
        return null;
    }

    @Override
    public Double getAverageVoting(Transmission transmission) {
        return null;
    }
}
