package org.example.service;

import org.example.dto.SingerDto;
import org.example.entities.Singer;
import org.example.repository.SingerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for the Singer entity.
 * This class is annotated with @Service and provides business logic for Singer-related operations.
 * It uses the SingerRepository to interact with the database and ModelMapper for mapping between Singer and SingerDto objects.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Service
public class SingerService {

    /**
     * The SingerRepository used to perform database operations.
     */
    private final SingerRepository singerRepository;

    /**
     * The ModelMapper used for mapping between Singer and SingerDto objects.
     */
    private final ModelMapper modelMapper;

    /**
     * Constructor that injects the SingerRepository and ModelMapper.
     *
     * @param singerRepository The SingerRepository to be used for database operations.
     * @param modelMapper      The ModelMapper to be used for mapping.
     */
    public SingerService(SingerRepository singerRepository, ModelMapper modelMapper) {
        this.singerRepository = singerRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves a list of all singers as SingerDto objects.
     *
     * @return A list of SingerDto objects.
     */
    public List<SingerDto> getAllSingers() {
        List<SingerDto> singerDtos = new ArrayList<>();

        singerRepository.findAll().forEach(s ->
                singerDtos.add(modelMapper.map(s, SingerDto.class)));

        return singerDtos;
    }

    /**
     * Retrieves a singer by their ID as a SingerDto object.
     *
     * @param id The ID of the singer to retrieve.
     * @return The SingerDto object with the specified ID.
     */
    public SingerDto getSingerById(Long id) {
        return modelMapper.map(singerRepository.findById(id), SingerDto.class);
    }

    /**
     * Creates a new singer.
     *
     * @param singerDto The SingerDto object containing the details of the singer to be created.
     * @return true if the creation was successful, false otherwise.
     */
    public boolean createSinger(SingerDto singerDto) {
        Singer singer = modelMapper.map(singerDto, Singer.class);
        return singerRepository.create(singer);
    }

    /**
     * Updates an existing singer.
     *
     * @param id        The ID of the singer to update.
     * @param singerDto The SingerDto object containing the updated details of the singer.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateSinger(Long id, SingerDto singerDto) {
        return singerRepository.update(modelMapper.map(singerDto, Singer.class));
    }

    /**
     * Deletes a singer by their ID.
     *
     * @param id The ID of the singer to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteSinger(Long id) {
        return singerRepository.delete(id);
    }
}
