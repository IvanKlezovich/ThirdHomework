//package org.example.service;
//
//import org.example.dto.SingerDto;
//import org.example.repository.SingerRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class SingerService {
//    SingerRepository singerRepository;
//    ModelMapper modelMapper;
//
//    public SingerService(SingerRepository singerRepository,
//                         ModelMapper modelMapper) {
//        this.singerRepository = singerRepository;
//        this.modelMapper = modelMapper;
//    }
//
//    public List<SingerDto> getAllSingers() {
//        List<SingerDto> singerDtos = new ArrayList<>();
//
//        singerRepository.findAll().forEach(s ->
//                singerDtos.add(modelMapper.map(s, SingerDto.class)));
//
//        return singerDtos;
//    }
//}
