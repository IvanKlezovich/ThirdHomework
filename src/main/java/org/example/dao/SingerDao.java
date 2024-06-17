package org.example.dao;

import org.example.entities.Singer;

public interface SingerDao {
    Iterable<Singer> findAll();
    String findById(long id);
    boolean save(Singer singer);
    boolean delete(Singer singer);
}
