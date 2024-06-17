package org.example.controller;

import org.example.service.SingerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/singer")
public class SingerController {

    SingerService singerService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @RequestMapping("/all")
    public String all(){
        return singerService.someSay();
    }
}
