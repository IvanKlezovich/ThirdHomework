package org.example.service;

import org.example.dto.AlbumDto;
import org.example.entities.Album;
import org.example.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for the Album entity.
 * This class is annotated with @Service and provides business logic for Album-related operations.
 * It uses the AlbumRepository to interact with the database and ModelMapper for mapping between Album and AlbumDto objects.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Service
public class AlbumService {

    /**
     * The AlbumRepository used to perform database operations.
     */
    private final AlbumRepository albumRepository;

    /**
     * The ModelMapper used for mapping between Album and AlbumDto objects.
     */
    private final ModelMapper modelMapper;

    /**
     * Constructor that injects the AlbumRepository and ModelMapper.
     *
     * @param albumRepository The AlbumRepository to be used for database operations.
     * @param modelMapper     The ModelMapper to be used for mapping.
     */
    public AlbumService(AlbumRepository albumRepository, ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves a list of all albums as AlbumDto objects.
     *
     * @return A list of AlbumDto objects.
     */
    public List<AlbumDto> getAllAlbums() {
        List<AlbumDto> albumDtos = new ArrayList<>();

        albumRepository.findAll().forEach(album ->
                albumDtos.add(modelMapper.map(album, AlbumDto.class)));

        return albumDtos;
    }

    /**
     * Retrieves an album by its ID as an AlbumDto object.
     *
     * @param id The ID of the album to retrieve.
     * @return The AlbumDto object with the specified ID.
     */
    public AlbumDto getAlbumById(Long id) {
        return modelMapper.map(albumRepository.findById(id), AlbumDto.class);
    }

    /**
     * Creates a new album.
     *
     * @param albumDto The AlbumDto object containing the details of the album to be created.
     * @return true if the creation was successful, false otherwise.
     */
    public boolean createAlbum(AlbumDto albumDto) {
        Album album = modelMapper.map(albumDto, Album.class);
        return albumRepository.create(album);
    }

    /**
     * Updates an existing album.
     *
     * @param id       The ID of the album to update.
     * @param albumDto The AlbumDto object containing the updated details of the album.
     * @return true if the update was successful, false otherwise.
     */
    public boolean updateAlbum(long id, AlbumDto albumDto) {
        return albumRepository.update(modelMapper.map(albumDto, Album.class));
    }

    /**
     * Deletes an album by its ID.
     *
     * @param id The ID of the album to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    public boolean deleteAlbum(Long id) {
        return albumRepository.delete(id);
    }
}
