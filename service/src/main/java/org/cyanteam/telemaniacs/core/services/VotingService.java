package org.cyanteam.telemaniacs.core.services;

import java.util.List;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.User;
import org.cyanteam.telemaniacs.core.entities.Voting;

/**
 * Interface for voting services.
 * @author Miroslav Kubus
 */
public interface VotingService {
    
    /**
     * Create a new voting
     * @param voting to be created
     */
    void createVoting(Voting voting);

    /**
     * Update voting
     * @param voting to be updated
     */
    void updateVoting(Voting voting);

    /**
     * Remove voting
     * @param voting to be deleted
     */
    void removeVoting(Voting voting);

    /**
     * Get votings by transmission
     * @param transmission which votings will be returned
     * @return Votings of given transmission
     */
    List<Voting> getVotingsByTransmission(Transmission transmission);

    /**
     * Get voting by user
     * @param user whose votings will be returned
     * @return Votings of given user
     */
    List<Voting> getVotingByUser(User user);
}
