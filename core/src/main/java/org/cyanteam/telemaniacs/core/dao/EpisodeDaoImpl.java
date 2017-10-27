package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.Episode;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EpisodeDaoImpl implements EpisodeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Episode episode) {
        if (episode == null) {
            throw new IllegalArgumentException("episode parameter cannot be null.");
        }

        entityManager.persist(episode);
    }

    @Override
    public void remove(Episode episode) {
        if (episode == null) {
            throw new IllegalArgumentException("episode parameter cannot be null.");
        }

        entityManager.remove(findById(episode.getId()));
    }

    @Override
    public void update(Episode episode) {
        if (episode == null) {
            throw new IllegalArgumentException("episode parameter cannot be null.");
        }

        if (findById(episode.getId()) == null) {
            throw new IllegalArgumentException("Episode does not exist.");
        }

        entityManager.merge(episode);
    }

    @Override
    public Episode findById(Long id) {
        if (id == null){
            throw new IllegalArgumentException("id parameter cannot be null.");
        }

        return entityManager.find(Episode.class, id);
    }
}
