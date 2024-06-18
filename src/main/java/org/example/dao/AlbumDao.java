package org.example.dao;

import org.example.entities.Album;

import java.util.List;

public interface AlbumDao {
    List<Album> findAll();
    Album findById(Long id);
    boolean update(Album album);
    boolean create(Album album);
    boolean delete(Long id);
}
