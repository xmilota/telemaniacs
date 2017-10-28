package org.cyanteam.telemaniacs.core.dao;

import org.cyanteam.telemaniacs.core.ApplicationContextConfiguration;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.enums.AgeAvailability;
import org.cyanteam.telemaniacs.core.enums.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = ApplicationContextConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TransmissionDaoTest {
    @Autowired
    private TransmissionDao transmissionDao;

    private Transmission transmission1;
    private Transmission transmission2;

    @Before
    @Transactional
    public void setUp() {
        transmission1 = createTransmission(
                "The Shawshank Redemption",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                142, AgeAvailability.AGE15, "EN", Type.MOVIE);

        transmission2 = createTransmission(
                "Football",
                "Ball pushed by a foot.",
                120, AgeAvailability.AGE12, "GR", Type.SPORT_EVENT);

        transmissionDao.create(transmission1);
        transmissionDao.create(transmission2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNullArgument() {
        transmissionDao.create(null);
    }

    private Transmission createTransmission(String name, String description,
                                            int length, AgeAvailability ageAvailability,
                                            String language, Type type) {
        Transmission transmission = new Transmission();
        transmission.setName(name);
        transmission.setDescription(description);
        transmission.setLenght(length);
        transmission.setAgeAvailability(ageAvailability);
        transmission.setLanguage(language);
        transmission.setType(type);

        return transmission;
    }
}