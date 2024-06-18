package org.example.service;

import org.example.dto.InstrumentDto;
import org.example.entities.Instrument;
import org.example.repository.InstrumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstrumentService {
    private final InstrumentRepository instrumentRepository;
    private final ModelMapper modelMapper;

    public InstrumentService(InstrumentRepository instrumentRepository,
                        ModelMapper modelMapper) {
        this.instrumentRepository = instrumentRepository;
        this.modelMapper = modelMapper;
    }

    public List<InstrumentDto> getAllInstruments() {
        List<InstrumentDto> instrumentDtos = new ArrayList<>();

        instrumentRepository.findAll().forEach(instrument ->
                instrumentDtos.add(modelMapper.map(instrument, InstrumentDto.class)));

        return instrumentDtos;
    }

    public InstrumentDto getInstrumentById(String id) {
        return modelMapper.map(instrumentRepository.findById(id), InstrumentDto.class);
    }

    public boolean createInstrument(InstrumentDto instrumentDto) {
        Instrument instrument = modelMapper.map(instrumentDto, Instrument.class);
        return instrumentRepository.create(instrument);
    }

    public boolean updateInstrument(String id, InstrumentDto instrumentDto) {
        return instrumentRepository.update(modelMapper.map(instrumentDto, Instrument.class));
    }

    public boolean deleteInstrument(String id) {
        return instrumentRepository.delete(id);
    }
}
