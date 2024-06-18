package org.example.service;

import org.example.dto.AlbumDto;
import org.example.entities.Album;
import org.example.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public AlbumService(AlbumRepository albumRepository,
                        ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    public List<AlbumDto> getAllAlbums() {
        List<AlbumDto> albumDtos = new ArrayList<>();

        albumRepository.findAll().forEach(album ->
                albumDtos.add(modelMapper.map(album, AlbumDto.class)));

        return albumDtos;
    }

    public AlbumDto getAlbumById(Long id) {
        return modelMapper.map(albumRepository.findById(id), AlbumDto.class);
    }

    public boolean createAlbum(AlbumDto albumDto) {
        Album album = modelMapper.map(albumDto, Album.class);
        return albumRepository.create(album);
    }

    public boolean updateAlbum(long id, AlbumDto albumDto) {
        return albumRepository.update(modelMapper.map(albumDto, Album.class));
    }

    public boolean deleteAlbum(Long id) {
        return albumRepository.delete(id);
    }
}
