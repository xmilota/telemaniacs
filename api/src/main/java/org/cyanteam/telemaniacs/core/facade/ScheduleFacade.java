package org.cyanteam.telemaniacs.core.facade;

import org.cyanteam.telemaniacs.core.dto.ChannelDTO;
import org.cyanteam.telemaniacs.core.dto.Schedule;
import org.cyanteam.telemaniacs.core.dto.ScheduleDTO;
import org.cyanteam.telemaniacs.core.dto.UserDTO;
import org.cyanteam.telemaniacs.core.entities.Channel;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author Simona Tinkova
 */
public interface ScheduleFacade {
	ScheduleDTO getSchedule(Collection<ChannelDTO> channels, LocalDateTime start, LocalDateTime end);

	ScheduleDTO getUserSchedule(UserDTO user, LocalDateTime from, LocalDateTime to);
}
