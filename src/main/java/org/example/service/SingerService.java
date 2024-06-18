package org.example.service;

import org.example.dto.SingerDto;
import org.example.entities.Singer;
import org.example.repository.SingerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SingerService {

    SingerRepository singerRepository;
    ModelMapper modelMapper;

    public SingerService(SingerRepository singerRepository,
                         ModelMapper modelMapper) {
        this.singerRepository = singerRepository;
        this.modelMapper = modelMapper;
    }

    public List<SingerDto> getAllSingers() {
        List<SingerDto> singerDtos = new ArrayList<>();

        singerRepository.findAll().forEach(s ->
                singerDtos.add(modelMapper.map(s, SingerDto.class)));

        return singerDtos;
    }

    public SingerDto getSingerById(Long id) {
        return modelMapper.map(singerRepository.findById(id), SingerDto.class);
    }

    public boolean createSinger(SingerDto singerDto) {
        Singer singer = modelMapper.map(singerDto, Singer.class);
        return singerRepository.create(singer);
    }

    public boolean updateSinger(Long id, SingerDto singerDto) {
        return singerRepository.update(modelMapper.map(singerDto, Singer.class));
    }

    public boolean deleteSinger(Long id) {
        return singerRepository.delete(id);
    }
}
