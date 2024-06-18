package org.example.repository;

import org.example.dao.SingerDao;
import org.example.entities.Singer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Repository implementation for the Singer entity using JDBC.
 * This class is annotated with @Repository and implements the SingerDao interface.
 * It uses a JdbcTemplate to interact with the database and a custom RowMapper to map result sets to Singer objects.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Repository
public class SingerRepository implements SingerDao {

    /**
     * The JdbcTemplate used to execute SQL queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor that injects the JdbcTemplate.
     *
     * @param jdbcTemplate The JdbcTemplate to be used for database interactions.
     */
    public SingerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Singer> findAll() {
        return jdbcTemplate.query("SELECT * FROM singer", new SingerRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Singer findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM singer WHERE id = ?", new Object[]{id}, new SingerRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Singer singer) {
        try {
            jdbcTemplate.update("UPDATE singer SET first_name = ?, last_name = ?, birth_date = ?, version = ? WHERE id = ?",
                    singer.getFirstname(), singer.getLastname(), singer.getBirthDate(), singer.getVersion(), singer.getId());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(Singer singer) {
        try {
            jdbcTemplate.update("INSERT INTO singer (first_name, last_name, birth_date, version) VALUES (?, ?, ?, ?)",
                    singer.getFirstname(), singer.getLastname(), singer.getBirthDate(), singer.getVersion());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Long id) {
        try {
            jdbcTemplate.update("DELETE FROM singer WHERE id = ?", id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Custom RowMapper implementation for mapping result sets to Singer objects.
     */
    private static class SingerRowMapper implements RowMapper<Singer> {
        @Override
        public Singer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String firstname = rs.getString("first_name");
            String lastname = rs.getString("last_name");
            Date birthDate = rs.getDate("birth_date");
            Integer version = rs.getInt("version");

            return new Singer(id, firstname, lastname, birthDate, version);
        }
    }
}
