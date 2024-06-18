package org.example.controller;

import org.example.dto.SingerDto;
import org.example.service.SingerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {

    SingerService singerService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping
    public ResponseEntity<List<SingerDto>> getAll(){
        if(singerService.getAllSingers() != null)
            return ResponseEntity.status(200).body(singerService.getAllSingers());
        return ResponseEntity.status(404).body(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingerDto> getSingerById(@PathVariable Long id){
        if(singerService.getSingerById(id)!=null)
            return ResponseEntity.status(200).body(singerService.getSingerById(id));
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping
    public ResponseEntity<String> createSinger(@RequestBody SingerDto singerDto){
        if(singerService.createSinger(singerDto))
            return ResponseEntity.status(200).body("Singer created");
        return ResponseEntity.status(404).body("Singer not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody SingerDto singerDto) {
        if(singerService.updateSinger(id, singerDto))
            return ResponseEntity.status(201).body("success");
        return ResponseEntity.status(404).body("fail");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if(singerService.deleteSinger(id))
            return ResponseEntity.status(204).body("success");
        return ResponseEntity.status(404).body("fail");
    }
}
