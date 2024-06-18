package org.example.controller;

import org.example.dto.SingerDto;
import org.example.service.SingerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling HTTP requests related to singers.
 * This class is annotated with @RestController and @RequestMapping to specify the base path for singer-related endpoints.
 * It uses the SingerService to perform operations such as retrieving, creating, updating, and deleting singers.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    /**
     * The SingerService used to perform business logic related to singers.
     */
    SingerService singerService;

    /**
     * Constructor that injects the SingerService.
     *
     * @param singerService The SingerService to be used by this controller.
     */
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    /**
     * Handles GET requests to retrieve all singers.
     *
     * @return ResponseEntity containing a list of SingerDto objects or a 404 status if no singers are found.
     */
    @GetMapping
    public ResponseEntity<List<SingerDto>> getAll() {
        List<SingerDto> singers = singerService.getAllSingers();
        if (singers != null)
            return ResponseEntity.status(200).body(singers);
        return ResponseEntity.status(404).body(new ArrayList<>());
    }

    /**
     * Handles GET requests to retrieve a singer by their ID.
     *
     * @param id The ID of the singer to retrieve.
     * @return ResponseEntity containing the SingerDto object or a 404 status if the singer is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SingerDto> getSingerById(@PathVariable Long id) {
        SingerDto singer = singerService.getSingerById(id);
        if (singer != null)
            return ResponseEntity.status(200).body(singer);
        return ResponseEntity.status(404).body(null);
    }

    /**
     * Handles POST requests to create a new singer.
     *
     * @param singerDto The SingerDto object containing the details of the singer to be created.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @PostMapping
    public ResponseEntity<String> createSinger(@RequestBody SingerDto singerDto) {
        if (singerService.createSinger(singerDto))
            return ResponseEntity.status(200).body("Singer created");
        return ResponseEntity.status(404).body("Singer not created");
    }

    /**
     * Handles PUT requests to update an existing singer.
     *
     * @param id        The ID of the singer to update.
     * @param singerDto The SingerDto object containing the updated details of the singer.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody SingerDto singerDto) {
        if (singerService.updateSinger(id, singerDto))
            return ResponseEntity.status(201).body("success");
        return ResponseEntity.status(404).body("fail");
    }

    /**
     * Handles DELETE requests to remove a singer by their ID.
     *
     * @param id The ID of the singer to delete.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        if(singerService.deleteSinger(id))
            return ResponseEntity.status(204).body("success");
        return ResponseEntity.status(404).body("fail");
    }
}
