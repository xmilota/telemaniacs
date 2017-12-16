package org.cyanteam.telemaniacs.rest.controllers;

import org.cyanteam.telemaniacs.core.dto.ScheduleDTO;
import org.cyanteam.telemaniacs.core.facade.ScheduleFacade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDate;

@RestController
@RequestMapping(Url.SCHEDULE)
public class ScheduleController {
    @Inject
    private ScheduleFacade scheduleFacade;

    @RequestMapping(value = "/date/{date}", method = RequestMethod.GET)
    public ScheduleDTO getSchedule(@PathVariable("date") long inputDate) {
        LocalDate date = LocalDate.now();

        return scheduleFacade.getSchedule(date);
    }
}
