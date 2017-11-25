package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.enums.TransmissionType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Simona Tinkova
 */
@Transactional
@Repository
public class TransmissionDaoImpl implements TransmissionDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Transmission create(Transmission transmission) {
		if (transmission == null){
			throw new IllegalArgumentException("Transmission is null");
		}
		entityManager.persist(transmission);
		return transmission;
	}

	@Override
	public Transmission delete(Transmission transmission) {
		if(transmission == null){
			throw new IllegalArgumentException("You are trying to delete null transmission");
		}

		entityManager.remove(findById(transmission.getId()));
		return transmission;
	}

	@Override
	public Transmission update(Transmission transmission) {
		if(transmission == null) {
			throw new IllegalArgumentException("You are trying to update null transmission.");
		}
		if (findById(transmission.getId()) == null) {
			throw new IllegalArgumentException("Transmission is not stored in the datebase.");
		}

		entityManager.merge(transmission);
		return transmission;
	}
	@Override
	public Transmission findById(Long id) {
		if (id == null){
			throw new IllegalArgumentException("id is null");
		}
		return entityManager.find(Transmission.class, id);
	}

	@Override
	public Transmission findByName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("You tried to find transmission with null name!");
		}
		return entityManager
				.createQuery("SELECT u FROM Transmission u WHERE u.name = :name", Transmission.class)
				.setParameter("name", name)
				.getSingleResult();
	}

	@Override
	public Transmission findByType(TransmissionType type) {
		if(type == null) {
			throw new IllegalArgumentException("You tried to find transmission with null type!");
		}
		return entityManager
				.createQuery("SELECT u FROM Transmission u WHERE u.transmissionType = :type", Transmission.class)
				.setParameter("type", type)
				.getSingleResult();
	}


}
