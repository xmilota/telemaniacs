package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.ApplicationContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.Channel;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.entities.TransmissionOccurrence;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.ChannelType;
import org.cyanteam.telemaniacs.core.enums.Type;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by tinko on 10/29/2017.
 */

@ContextConfiguration(classes = ApplicationContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TransmissionOccurenceDaoTest {
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
	private TransmissionOccurrence occurence1;
	private TransmissionOccurrence occurence2;
	private Transmission transmission1;
	private Transmission transmission2;

	@Before
	@Transactional
	public void init() {
		prepareChannel1();
		channelDao.create(channel1);

		prepareTransmission1();
		transmissionDao.create(transmission1);

		occurence1 = new TransmissionOccurrence();
		occurence1.setName("Test occurence name 1");
		occurence1.setStartDate(LocalDateTime.now());
		occurence1.setRerun(false);
		occurence1.setTransmission(transmission1);
		occurence1.setChannel(channel1);

		transmissionOccurrenceDao.create(occurence1);

		occurence2 = new TransmissionOccurrence();
		occurence2.setName("Test occurence name 2");
		occurence2.setStartDate(LocalDateTime.now());
		occurence2.setRerun(true);
		occurence2.setTransmission(transmission2);
		occurence2.setChannel(channel2);

		transmissionOccurrenceDao.create(occurence2);

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
