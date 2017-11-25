package org.cyanteam.telemaniacs.core.services;

import org.cyanteam.telemaniacs.core.ServiceContextConfiguration;
import org.cyanteam.telemaniacs.core.dao.TransmissionDao;
import org.cyanteam.telemaniacs.core.entities.Transmission;
import org.cyanteam.telemaniacs.core.helpers.TransmissionBuilder;
import org.hibernate.service.spi.ServiceException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * @author Simona Tinkova
 */

@ContextConfiguration(classes = ServiceContextConfiguration.class)
public class TransmissionServiceImplTest extends AbstractTestNGSpringContextTests {
	@Mock
	private TransmissionDao transmissionDao;

	@Autowired
	@InjectMocks
	private TransmissionService transmissionService;

	private Transmission transmission1;
	private Transmission transmission2;
	private Transmission transmission3;

	private Long counter = 10L;
	private Map<Long, Transmission> transmissions = new HashMap<>();

	@BeforeClass
	public void beforeClass() throws ServiceException {
		MockitoAnnotations.initMocks(this);

		when(transmissionDao.create(any(Transmission.class))).then(invoke -> {
			Transmission mockedTransmission = invoke.getArgumentAt(0, Transmission.class);
			if (mockedTransmission.getId() != null) {
				throw new IllegalArgumentException("Transmission already exist");
			}
			if (mockedTransmission.getName() == null){
				throw new IllegalArgumentException("Transmission name can't be null");
			}
			if (checkTransmissionsNameDuplicity(mockedTransmission.getName(), -1L)) {
				throw new IllegalArgumentException("Transmission name already exist");
			}
			long index = counter;
			mockedTransmission.setId(index);
			transmissions.put(index, mockedTransmission);
			counter++;
			return mockedTransmission;
		});

		when(transmissionDao.update(any(Transmission.class))).then(invoke -> {
			Transmission mockedTransmission = invoke.getArgumentAt(0, Transmission.class);
			if (mockedTransmission.getId() == null) {
				throw new IllegalArgumentException("Transmission was not created yet.");
			}
			if (mockedTransmission.getName() == null){
				throw new IllegalArgumentException("Transmission name can't be null");
			}
			if (checkTransmissionsNameDuplicity(mockedTransmission.getName(), mockedTransmission.getId())) {
				throw new IllegalArgumentException("Transmission name already exist");
			}
			transmissions.replace(mockedTransmission.getId(), mockedTransmission);
			return mockedTransmission;
		});

		when(transmissionDao.delete(any(Transmission.class))).then(invoke -> {
			Transmission mockedTransmission = invoke.getArgumentAt(0, Transmission.class);
			if (mockedTransmission.getId() == null) {
				throw new IllegalArgumentException("Transmission is not in Database.");
			}
			transmissions.remove(mockedTransmission.getId(), mockedTransmission);
			return mockedTransmission;
		});


		when(transmissionDao.findById(anyLong())).then(invoke -> {
			long index = invoke.getArgumentAt(0, Long.class);
			return transmissions.get(index);
		});


	}


	@BeforeMethod
	public void beforeTest() {
		transmissions.clear();
		transmission1 = TransmissionBuilder.sampleFootballBuilder().build();
		transmission2 = TransmissionBuilder.sampleIceAgeBuilder().build();
		transmission3 = TransmissionBuilder.sampleShawshankBuilder().build();

		transmission1.setId(1L);
		transmission2.setId(2L);
		transmission3.setId(3L);

		transmissions.put(1L, transmission1);
		transmissions.put(2L, transmission2);
		transmissions.put(3L, transmission3);
	}


	@Test
	public void createNewTransmission() throws DataAccessException {
		int sizeBefore = transmissions.size();
		Transmission transmission = TransmissionBuilder.sampleFootballBuilder().build();
		transmissionService.createTransmission(transmission);
		assertThat(transmissions.values()).hasSize(sizeBefore + 1)
				.contains(transmission);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createNullTransmission() {
		transmissionService.createTransmission(null);
	}


	@Test(expected = DataAccessException.class)
	public void createTransmissionNullName(){
		transmissionService.createTransmission(new Transmission());
	}

	@Test(expected = DataAccessException.class)
	public void createExistingTransmission() {
		Transmission transmission = TransmissionBuilder.sampleFootballBuilder().build();
		Transmission anotherTransmission = TransmissionBuilder.sampleIceAgeBuilder().build();
		transmissionService.createTransmission(transmission);
		transmissionService.createTransmission(anotherTransmission);
	}

	@Test(expected = DataAccessException.class)
	public void createTransmissionWithIdNotNull() {
		Transmission transmission = TransmissionBuilder.sampleFootballBuilder().build();
		transmission.setId(1L);
		transmissionService.createTransmission(transmission);
	}

	@Test
	public void updateTransmission() throws DataAccessException {
		transmission1.setName("updated transmission");
		transmissionService.updateTransmission(transmission1);

		Transmission updated = transmissions.get(transmission1.getId());

		assertThat(updated.getName()).isEqualTo("updated transmission");
		assertThat(updated).isEqualToComparingFieldByField(transmission1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void updateNullTransmission() {
		transmissionService.updateTransmission(null);
	}

	@Test(expected = DataAccessException.class)
	public void updateTransmissionNullName(){
		transmission1.setName(null);
		transmissionService.updateTransmission(transmission1);
	}


	@Test(expected = DataAccessException.class)
	public void updateTransmissionWithNullId() {
		Transmission transmission = TransmissionBuilder.sampleFootballBuilder().build();
		transmissionService.updateTransmission(transmission);
	}

	@Test(expected = DataAccessException.class)
	public void updateTransmissionWithDuplicateName() {
		transmission1.setName(transmission2.getName());
		transmissionService.updateTransmission(transmission1);
	}


	@Test
	public void deleteTransmission() throws DataAccessException {
		int sizeBefore = transmissions.values().size();
		transmissionService.removeTransmission(transmission1);

		assertThat(transmissions.values()).hasSize(sizeBefore - 1)
				.doesNotContain(transmission1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void deleteNullTransmission() {
		transmissionService.removeTransmission(null);
	}


	@Test(expected = DataAccessException.class)
	public void deleteTransmissionWithNullId() {
		Transmission transmission = TransmissionBuilder.sampleFootballBuilder().build();
		transmissionService.removeTransmission(transmission);
	}

	@Test
	public void deleteTransmissionNotInDB() throws DataAccessException {
		int sizeBefore = transmissions.values().size();
		Transmission transmission = TransmissionBuilder.sampleFootballBuilder().build();
		transmission.setId(counter * 2L);
		transmissionService.removeTransmission(transmission);

		assertThat(transmissions.values()).hasSize(sizeBefore)
				.doesNotContain(transmission);

	}


	@Test
	public void findTransmissionById() throws DataAccessException {
		assertThat(transmissionService.getTransmissionById(transmission1.getId()))
				.isEqualToComparingFieldByField(transmission1);
	}

	@Test
	public void findTransmissionByIdNotInDB() throws DataAccessException {
		assertThat(transmissionService.getTransmissionById(10000L)).isNull();
	}

	@Test(expected = IllegalArgumentException.class)
	public void findTransmissionByNullId() {
		transmissionService.getTransmissionById(null);
	}

	private boolean checkTransmissionsNameDuplicity(String name, long id) {
		for (Transmission m : transmissions.values()) {
			if (m.getName().equals(name) && id != m.getId()) {
				return true;
			}
		}
		return false;
	}
}

