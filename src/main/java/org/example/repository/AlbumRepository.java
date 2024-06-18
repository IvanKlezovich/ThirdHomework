package org.example.repository;

import org.example.dao.AlbumDao;
import org.example.entities.Album;
import org.example.entities.Singer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AlbumRepository implements AlbumDao {

    private final JdbcTemplate jdbcTemplate;
    private final SingerRepository singerRepository;

    public AlbumRepository(JdbcTemplate jdbcTemplate, SingerRepository singerRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.singerRepository = singerRepository;
    }

    @Override
    public List<Album> findAll() {
        return jdbcTemplate.query("SELECT * FROM album", new AlbumRowMapper());
    }

    @Override
    public Album findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM album WHERE id = ?", new Object[]{id}, new AlbumRowMapper());
    }

    @Override
    public boolean update(Album album) {
        jdbcTemplate.update("UPDATE album SET title = ?, release_date = ?, version = ?, singer_id = ? WHERE id = ?",
                album.getTitle(), album.getReleaseDate(), album.getVersion(), album.getSinger().getId(), album.getId());
        return false;
    }

    @Override
    public boolean create(Album album) {
        jdbcTemplate.update("INSERT INTO album (title, release_date, version, singer_id) VALUES (?, ?, ?, ?)",
                album.getTitle(), album.getReleaseDate(), album.getVersion(), album.getSinger().getId());
        return false;
    }

    @Override
    public boolean delete(Long id) {
        jdbcTemplate.update("DELETE FROM album WHERE id = ?", id);
        return false;
    }

    private class AlbumRowMapper implements RowMapper<Album> {
        @Override
        public Album mapRow(ResultSet rs, int rowNum) throws SQLException {
            Album album = new Album();
            album.setId(rs.getLong("id"));
            album.setTitle(rs.getString("title"));
            album.setReleaseDate(rs.getDate("release_date"));
            album.setVersion(rs.getInt("version"));

            Singer singer = singerRepository.findById(rs.getLong("singer_id"));
            album.setSinger(singer);

            return album;
        }
    }
}
