package org.example.dao;

import org.example.entities.Instrument;

import java.util.List;

public interface InstrumentDao {
    List<Instrument> findAll();
    Instrument findById(String id);
    boolean update(Instrument instrument);
    boolean create(Instrument instrument);
    boolean delete(String id);
}
