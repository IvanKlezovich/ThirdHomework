package org.example.dao;

import org.example.entities.Instrument;

import java.util.List;

/**
 * Data Access Object (DAO) interface for the Instrument entity.
 * This interface defines the methods for performing CRUD operations on the Instrument entity.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
public interface InstrumentDao {

    /**
     * Retrieves a list of all instruments.
     *
     * @return A list of Instrument objects.
     */
    List<Instrument> findAll();

    /**
     * Retrieves an instrument by its ID.
     *
     * @param id The ID of the instrument to retrieve.
     * @return The Instrument object with the specified ID, or null if not found.
     */
    Instrument findById(String id);

    /**
     * Updates an existing instrument.
     *
     * @param instrument The Instrument object to update.
     * @return true if the update was successful, false otherwise.
     */
    boolean update(Instrument instrument);

    /**
     * Creates a new instrument.
     *
     * @param instrument The Instrument object to create.
     * @return true if the creation was successful, false otherwise.
     */
    boolean create(Instrument instrument);

    /**
     * Deletes an instrument by its ID.
     *
     * @param id The ID of the instrument to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean delete(String id);
}
