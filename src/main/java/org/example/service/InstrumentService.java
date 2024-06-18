package org.example.service;

import org.example.dto.InstrumentDto;
import org.example.entities.Instrument;
import org.example.repository.InstrumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for the Instrument entity.
 * This class is annotated with @Service and provides business logic for Instrument-related operations.
 * It uses the InstrumentRepository to interact with the database and ModelMapper for mapping between Instrument and InstrumentDto objects.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Service
public class InstrumentService {

    /**
     * The InstrumentRepository used to perform database operations.
     */
    private final InstrumentRepository instrumentRepository;

    /**
     * The ModelMapper used for mapping between Instrument and InstrumentDto objects.
     */
    private final ModelMapper modelMapper;

    /**
     * Constructor that injects the InstrumentRepository and ModelMapper.
     *
     * @param instrumentRepository The InstrumentRepository to be used for database operations.
     * @param modelMapper          The ModelMapper to be used for mapping.
     */
    public InstrumentService(InstrumentRepository instrumentRepository, ModelMapper modelMapper) {
        this.instrumentRepository = instrumentRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves a list of all instruments as InstrumentDto objects.
     *
     * @return A list of InstrumentDto objects.
     */
    public List<InstrumentDto> getAllInstruments() {
        List<InstrumentDto> instrumentDtos = new ArrayList<>();

        instrumentRepository.findAll().forEach(instrument ->
                instrumentDtos.add(modelMapper.map(instrument, InstrumentDto.class)));

        return instrumentDtos;
    }

    /**
     * Retrieves an instrument by its ID as an InstrumentDto object.
     *
     * @param id The ID of the instrument to retrieve.
     * @return The InstrumentDto object with the specified ID.
     */
    public InstrumentDto getInstrumentById(String id) {
        return modelMapper.map(instrumentRepository.findById(id), InstrumentDto.class);
    }

    /**
     * Creates a new instrument.
     *
     * @param instrumentDto The InstrumentDto object containing the details of the instrument to be created.
     * @return true if the creation was successful, false otherwise.
     */
    public boolean createInstrument(InstrumentDto instrumentDto) {
        Instrument instrument = modelMapper.map(instrumentDto, Instrument.class);
        return instrumentRepository.create(instrument);
    }

    /**
     * Updates an existing instrument.
     *
     * @param id            The ID of the instrument to update.
     * @param instrumentDto The InstrumentDto object containing the updated details of the instrument.
     * @return true if the update was successful, false otherwise.
     */
    public boolean deleteInstrument(String id) {
        return instrumentRepository.delete(id);
    }
}
