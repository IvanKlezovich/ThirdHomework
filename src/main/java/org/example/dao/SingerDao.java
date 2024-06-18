package org.example.dao;

import org.example.entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    Singer findById(Long id);
    boolean update(Singer singer);
    boolean create(Singer singer);
    boolean delete(Long id);
}
