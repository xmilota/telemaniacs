package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.Episode;

/**
 * DAO for episodes
 * @author Michael Le
 */
public interface EpisodeDao {
    /**
     * Creates a new episode
     * @param episode Episode entity
     * @throws IllegalArgumentException if episode is null
     */
    void create(Episode episode);

    /**
     * Removes the episode
     * @param episode Episode entity
     * @throws IllegalArgumentException if episode is null
     */
    void remove(Episode episode);

    /**
     * Updates the episode
     * @param episode Episode entity
     * @throws IllegalArgumentException if episode is null
     * @throws IllegalArgumentException if episode does not exist
     */
    void update(Episode episode);

    /**
     * Finds episode by ID
     * @param id Episode ID
     * @return Episode entity
     * @throws IllegalArgumentException if id is null
     */
    Episode findById(Long id);
}
