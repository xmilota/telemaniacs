package org.cyanteam.telemaniacs.rest.controllers;

import org.cyanteam.telemaniacs.core.dto.TransmissionCreateDTO;
import org.cyanteam.telemaniacs.core.dto.TransmissionDTO;
import org.cyanteam.telemaniacs.core.facade.TransmissionFacade;
import org.cyanteam.telemaniacs.rest.exceptions.ResourceNotFoundException;
import org.cyanteam.telemaniacs.rest.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping(Url.TRANSMISSION)
public class TransmissionController {

    @Inject
    private TransmissionFacade transmissionFacade;

    private final static Logger log = LoggerFactory.getLogger(TransmissionController.class);

    /**
     * Creates new transmission.
     *
     * @param transmissionCreateDTO transmission that will be created
     * @return created transmission
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransmissionDTO createTransmission(@RequestBody @Valid TransmissionCreateDTO transmissionCreateDTO,
                                              BindingResult bindingResult) {
        log.debug("rest createTransmission()");

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid transmission state.");
        }

        Long transmissionId = transmissionFacade.create(transmissionCreateDTO);
        return transmissionFacade.findById(transmissionId);
    }

    /**
     * Updates transmission.
     *
     * @param id ID of updated transmission
     * @param transmissionDTO transmission with modified values
     * @return updated transmission
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransmissionDTO updateTransmission(@PathVariable("id") long id,
                                              @RequestBody @Valid TransmissionDTO transmissionDTO,
                                              BindingResult bindingResult) {
        log.debug("rest updateTransmission({})");

        if (bindingResult.hasErrors()) {
            throw new ValidationException("Invalid transmission state.");
        }

        transmissionDTO.setId(id);
        return transmissionFacade.update(transmissionDTO);
    }

    /**
     * Retrieves transmission with specific ID
     *
     * @param id ID of the transmission
     * @return transmission with specific ID
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TransmissionDTO getTransmission(@PathVariable("id") long id) {
        log.debug("rest transmission({})");

        TransmissionDTO transmissionDTO = transmissionFacade.findById(id);
        if (transmissionDTO == null) {
            throw new ResourceNotFoundException("Transmission", id);
        }

        return transmissionDTO;
    }
}
