package org.example.dao;

import org.example.entities.Album;

import java.util.List;

/**
 * Data Access Object (DAO) interface for the Album entity.
 * This interface defines the methods for performing CRUD operations on the Album entity.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
public interface AlbumDao {

    /**
     * Retrieves a list of all albums.
     *
     * @return A list of Album objects.
     */
    List<Album> findAll();

    /**
     * Retrieves an album by its ID.
     *
     * @param id The ID of the album to retrieve.
     * @return The Album object with the specified ID, or null if not found.
     */
    Album findById(Long id);

    /**
     * Updates an existing album.
     *
     * @param album The Album object to update.
     * @return true if the update was successful, false otherwise.
     */
    boolean update(Album album);

    /**
     * Creates a new album.
     *
     * @param album The Album object to create.
     * @return true if the creation was successful, false otherwise.
     */
    boolean create(Album album);

    /**
     * Deletes an album by its ID.
     *
     * @param id The ID of the album to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean delete(Long id);
}
