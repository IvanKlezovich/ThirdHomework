package org.example.dao;

import org.example.entities.Singer;

import java.util.List;

/**
 * Data Access Object (DAO) interface for the Singer entity.
 * This interface defines the methods for performing CRUD operations on the Singer entity.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
public interface SingerDao {

    /**
     * Retrieves a list of all singers.
     *
     * @return A list of Singer objects.
     */
    List<Singer> findAll();

    /**
     * Retrieves a singer by their ID.
     *
     * @param id The ID of the singer to retrieve.
     * @return The Singer object with the specified ID, or null if not found.
     */
    Singer findById(Long id);

    /**
     * Updates an existing singer.
     *
     * @param singer The Singer object to update.
     * @return true if the update was successful, false otherwise.
     */
    boolean update(Singer singer);

    /**
     * Creates a new singer.
     *
     * @param singer The Singer object to create.
     * @return true if the creation was successful, false otherwise.
     */
    boolean create(Singer singer);

    /**
     * Deletes a singer by their ID.
     *
     * @param id The ID of the singer to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean delete(Long id);
}
