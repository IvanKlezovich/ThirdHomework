package org.example.controller;

import org.example.dto.InstrumentDto;
import org.example.service.InstrumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instrument")
public class InstrumentController {
    InstrumentService instrumentService;

    public InstrumentController(InstrumentService instrumentService) {
        this.instrumentService = instrumentService;
    }

    @GetMapping
    public ResponseEntity<List<InstrumentDto>> getAll(){
        if(instrumentService.getAllInstruments() != null)
            return ResponseEntity.status(200).body(instrumentService.getAllInstruments());
        return ResponseEntity.status(404).body(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentDto> getInstrumentById(@PathVariable String id){
        if(instrumentService.getInstrumentById(id).equals(null))
            return ResponseEntity.status(200).body(instrumentService.getInstrumentById(id));
        return ResponseEntity.status(404).body(null);
    }

    @PostMapping
    public ResponseEntity<String> createInstrument(@RequestBody InstrumentDto instrumentDto){
        if(instrumentService.createInstrument(instrumentDto))
            return ResponseEntity.status(200).body("Instrument created");
        return ResponseEntity.status(404).body("Instrument not created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody InstrumentDto instrumentDto) {
        if(instrumentService.updateInstrument(id, instrumentDto))
            return ResponseEntity.status(201).body("success");
        return ResponseEntity.status(404).body("fail");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        if(instrumentService.deleteInstrument(id))
            return ResponseEntity.status(204).body("success");
        return ResponseEntity.status(404).body("fail");
    }
}
