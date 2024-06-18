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

/**
 * Repository implementation for the Album entity using JDBC.
 * This class is annotated with @Repository and implements the AlbumDao interface.
 * It uses a JdbcTemplate to interact with the database and a custom RowMapper to map result sets to Album objects.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Repository
public class AlbumRepository implements AlbumDao {

    /**
     * The JdbcTemplate used to execute SQL queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * The SingerRepository used to fetch Singer objects.
     */
    private final SingerRepository singerRepository;

    /**
     * Constructor that injects the JdbcTemplate and SingerRepository.
     *
     * @param jdbcTemplate      The JdbcTemplate to be used for database interactions.
     * @param singerRepository  The SingerRepository to be used for fetching Singer objects.
     */
    public AlbumRepository(JdbcTemplate jdbcTemplate, SingerRepository singerRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.singerRepository = singerRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Album> findAll() {
        return jdbcTemplate.query("SELECT * FROM album", new AlbumRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Album findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM album WHERE id = ?", new Object[]{id}, new AlbumRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Album album) {
        jdbcTemplate.update("UPDATE album SET title = ?, release_date = ?, version = ?, singer_id = ? WHERE id = ?",
                album.getTitle(), album.getReleaseDate(), album.getVersion(), album.getSinger().getId(), album.getId());
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(Album album) {
        jdbcTemplate.update("INSERT INTO album (title, release_date, version, singer_id) VALUES (?, ?, ?, ?)",
                album.getTitle(), album.getReleaseDate(), album.getVersion(), album.getSinger().getId());
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        jdbcTemplate.update("DELETE FROM album WHERE id = ?", id);
        return false;
    }

    /**
     * Custom RowMapper implementation for mapping result sets to Album objects.
     */
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
