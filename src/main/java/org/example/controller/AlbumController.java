package org.example.controller;

import org.example.dto.AlbumDto;
import org.example.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling HTTP requests related to albums.
 * This class is annotated with @RestController and @RequestMapping to specify the base path for album-related endpoints.
 * It uses the AlbumService to perform operations such as retrieving, creating, updating, and deleting albums.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@RestController
@RequestMapping("/albums")
public class AlbumController {

    /**
     * The AlbumService used to perform business logic related to albums.
     */
    private final AlbumService albumService;

    /**
     * Constructor that injects the AlbumService.
     *
     * @param albumService The AlbumService to be used by this controller.
     */
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    /**
     * Handles GET requests to retrieve all albums.
     *
     * @return ResponseEntity containing a list of AlbumDto objects or a 404 status if no albums are found.
     */
    @GetMapping
    public ResponseEntity<List<AlbumDto>> getAll() {
        List<AlbumDto> albums = albumService.getAllAlbums();
        if (albums != null)
            return ResponseEntity.status(200).body(albums);
        return ResponseEntity.status(404).body(new ArrayList<>());
    }

    /**
     * Handles GET requests to retrieve an album by its ID.
     *
     * @param id The ID of the album to retrieve.
     * @return ResponseEntity containing the AlbumDto object or a 404 status if the album is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDto> getAlbumById(@PathVariable Long id) {
        AlbumDto album = albumService.getAlbumById(id);
        if (album != null)
            return ResponseEntity.status(200).body(album);
        return ResponseEntity.status(404).body(null);
    }

    /**
     * Handles POST requests to create a new album.
     *
     * @param albumDto The AlbumDto object containing the details of the album to be created.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @PostMapping
    public ResponseEntity<String> createAlbum(@RequestBody AlbumDto albumDto) {
        if (albumService.createAlbum(albumDto))
            return ResponseEntity.status(200).body("Album created");
        return ResponseEntity.status(404).body("Album not created");
    }

    /**
     * Handles PUT requests to update an existing album.
     *
     * @param id        The ID of the album to update.
     * @param albumDto  The AlbumDto object containing the updated details of the album.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody AlbumDto albumDto) {
        if (albumService.updateAlbum(id, albumDto))
            return ResponseEntity.status(201).body("success");
        return ResponseEntity.status(404).body("fail");
    }

    /**
     * Handles DELETE requests to remove an album by its ID.
     *
     * @param id The ID of the album to delete.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if(albumService.deleteAlbum(id))
            return ResponseEntity.status(204).body("success");
        return ResponseEntity.status(404).body("fail");
    }
}
