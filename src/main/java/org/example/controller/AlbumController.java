package org.example.controller;

import org.example.dto.AlbumDto;
import org.example.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAll(){
        if(albumService.getAllAlbums() != null)
            return ResponseEntity.status(200).body(albumService.getAllAlbums());
        return ResponseEntity.status(404).body(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbumById(@PathVariable Long id){
        if(albumService.getAlbumById(id)!=null)
            return ResponseEntity.status(200).body(albumService.getAlbumById(id));
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping
    public ResponseEntity<String> createAlbum(@RequestBody AlbumDto albumDto){
        if(albumService.createAlbum(albumDto))
            return ResponseEntity.status(200).body("Album created");
        return ResponseEntity.status(404).body("Album not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody AlbumDto albumDto) {
        if(albumService.updateAlbum(id, albumDto))
            return ResponseEntity.status(201).body("success");
        return ResponseEntity.status(404).body("fail");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if(albumService.deleteAlbum(id))
            return ResponseEntity.status(204).body("success");
        return ResponseEntity.status(404).body("fail");
    }
}
