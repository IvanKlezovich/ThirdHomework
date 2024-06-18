package org.example.controller;

import org.example.dto.InstrumentDto;
import org.example.service.InstrumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling HTTP requests related to instruments.
 * This class is annotated with @RestController and @RequestMapping to specify the base path for instrument-related endpoints.
 * It uses the InstrumentService to perform operations such as retrieving, creating, updating, and deleting instruments.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@RestController
@RequestMapping("/instrument")
public class InstrumentController {

    /**
     * The InstrumentService used to perform business logic related to instruments.
     */
    InstrumentService instrumentService;

    /**
     * Constructor that injects the InstrumentService.
     *
     * @param instrumentService The InstrumentService to be used by this controller.
     */
    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    /**
     * Handles GET requests to retrieve all instruments.
     *
     * @return ResponseEntity containing a list of InstrumentDto objects or a 404 status if no instruments are found.
     */
    @GetMapping
    public ResponseEntity<List<InstrumentDto>> getAll() {
        List<InstrumentDto> instruments = instrumentService.getAllInstruments();
        if (instruments != null)
            return ResponseEntity.status(200).body(instruments);
        return ResponseEntity.status(404).body(new ArrayList<>());
    }

    /**
     * Handles GET requests to retrieve an instrument by its ID.
     *
     * @param id The ID of the instrument to retrieve.
     * @return ResponseEntity containing the InstrumentDto object or a 404 status if the instrument is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InstrumentDto> getInstrumentById(@PathVariable String id) {
        InstrumentDto instrument = instrumentService.getInstrumentById(id);
        if (instrument != null)
            return ResponseEntity.status(200).body(instrument);
        return ResponseEntity.status(404).body(null);
    }

    /**
     * Handles POST requests to create a new instrument.
     *
     * @param instrumentDto The InstrumentDto object containing the details of the instrument to be created.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @PostMapping
    public ResponseEntity<String> createInstrument(@RequestBody InstrumentDto instrumentDto) {
        if (instrumentService.createInstrument(instrumentDto))
            return ResponseEntity.status(200).body("Instrument created");
        return ResponseEntity.status(404).body("Instrument not created");
    }

    /**
     * Handles PUT requests to update an existing instrument.
     *
     * @param id            The ID of the instrument to update.
     * @param instrumentDto The InstrumentDto object containing the updated details of the instrument.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody InstrumentDto instrumentDto) {
        if (instrumentService.updateInstrument(id, instrumentDto))
            return ResponseEntity.status(201).body("success");
        return ResponseEntity.status(404).body("fail");
    }

    /**
     * Handles DELETE requests to remove an instrument by its ID.
     *
     * @param id The ID of the instrument to delete.
     * @return ResponseEntity with a status and message indicating the success or failure of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        if(instrumentService.deleteInstrument(id))
            return ResponseEntity.status(204).body("success");
        return ResponseEntity.status(404).body("fail");
    }
}
