//package org.example.controller;
//
//import org.example.dto.SingerDto;
//import org.example.service.SingerService;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/singer")
//public class SingerController {
//
//    SingerService singerService;
//
//    public SingerController(SingerService singerService) {
//        this.singerService = singerService;
//    }
//
//    @RequestMapping("/all")
//    public List<SingerDto> getAll(){
//        return singerService.getAllSingers();
//    }
//}
