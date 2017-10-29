package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.ApplicationContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by tinko on 10/29/2017.
 */

@ContextConfiguration(classes = ApplicationContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TransmissionOccurrenceDaoTest {
	@PersistenceContext
	public EntityManager entityManager;

	@Autowired
	public ChannelDao channelDao;

	@Autowired
	public TransmissionOccurrenceDao transmissionOccurrenceDao;

	@Autowired
	public TransmissionDao transmissionDao;

	private Channel channel1;
	private Channel channel2;
	private TransmissionOccurrence occurrence1;
	private TransmissionOccurrence occurrence2;
	private Transmission transmission1;
	private Transmission transmission2;

	@Before
	@Transactional
	public void init() {
		prepareChannel1();
		channelDao.create(channel1);

		prepareTransmission1();
		transmissionDao.create(transmission1);

		occurrence1 = new TransmissionOccurrence();
		occurrence1.setName("Test occurence name 1");
		occurrence1.setStartDate(LocalDateTime.now());
		occurrence1.setRerun(false);
		occurrence1.setTransmission(transmission1);
		occurrence1.setChannel(channel1);

		transmissionOccurrenceDao.create(occurrence1);

		occurrence2 = new TransmissionOccurrence();
		occurrence2.setName("Test occurence name 2");
		occurrence2.setStartDate(LocalDateTime.now());
		occurrence2.setRerun(true);
		occurrence2.setTransmission(transmission2);
		occurrence2.setChannel(channel2);
	}

	@Test
	public void createOccurence(){
		transmissionOccurrenceDao.create(occurrence2);
		TransmissionOccurrence acOccurence = transmissionOccurrenceDao.findById(occurrence2.getId());

		assertThat(occurrence2.getId()).isNotNull();
		assertThat(occurrence2).isEqualToComparingFieldByField(acOccurence);
	}

	@Test(expected= PersistenceException.class)
	public void createWithSetIdTest() {
		occurrence2.setId(Long.MIN_VALUE);
		transmissionOccurrenceDao.create(occurrence2);
	}

	@Test(expected= PersistenceException.class)
	public void createWithNonUniqueNameTest() {
		occurrence2.setName(occurrence1.getName());
		transmissionOccurrenceDao.create(occurrence2);
	}

	@Test(expected= ConstraintViolationException.class)
	public void createWithNullNameTest() {
		occurrence2.setName(null);
		transmissionOccurrenceDao.create(occurrence2);
	}

	@Test(expected= IllegalArgumentException.class)
	public void createWithNullOccurrenceTest() {
		transmissionOccurrenceDao.create(null);
	}



	private void prepareChannel1(){
		channel1 = new Channel();
		channel1.setLanguage("CZECH");
		channel1.setName("Test name channel 1");
		channel1.setChannelType(ChannelType.CHILDREN);
		channel1.setTransmissionOccurrences(new ArrayList<>());
	}

	private void prepareChannel2(){
		channel2 = new Channel();
		channel2.setLanguage("ENGLISH");
		channel2.setName("Test name channel 2");
		channel2.setChannelType(ChannelType.SPORT);
		channel2.setTransmissionOccurrences(new ArrayList<>());
	}

	private void prepareTransmission1() {
		transmission1 = new Transmission();
		transmission1.setAgeAvailability(AgeAvailability.AGE15);
		transmission1.setDescription("Test transmission description 1");
		transmission1.setLanguage("CZECH");
		transmission1.setLenght(120);
		transmission1.setName("Test transmission name 1");
		transmission1.setType(Type.MOVIE);
		transmission1.setOccurrences(new ArrayList<>());
	}

	private void prepareTransmission2() {
		transmission2 = new Transmission();
		transmission2.setAgeAvailability(AgeAvailability.AGE12);
		transmission2.setDescription("Test transmission description 2");
		transmission2.setLanguage("ENGLISH");
		transmission2.setLenght(60);
		transmission2.setName("Test transmission name 2");
		transmission2.setType(Type.TV_SERIES);
		transmission2.setOccurrences(new ArrayList<>());
	}


}
