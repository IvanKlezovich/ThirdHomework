package org.example.repository;

import org.example.dao.InstrumentDao;
import org.example.dao.SingerDao;
import org.example.entities.Instrument;
import org.example.entities.Singer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository implementation for the Instrument entity using JDBC.
 * This class is annotated with @Repository and implements the InstrumentDao interface.
 * It uses a JdbcTemplate to interact with the database and a custom RowMapper to map result sets to Instrument objects.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
@Repository
public class InstrumentRepository implements InstrumentDao {

    /**
     * The JdbcTemplate used to execute SQL queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor that injects the JdbcTemplate.
     *
     * @param jdbcTemplate The JdbcTemplate to be used for database interactions.
     */
    public InstrumentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Instrument> findAll() {
        return jdbcTemplate.query("SELECT * FROM instrument", new InstrumentRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Instrument findById(String id) {
        return jdbcTemplate.queryForObject("SELECT * FROM instrument WHERE instrument_id = ?", new Object[]{id}, new InstrumentRowMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Instrument instrument) {
        jdbcTemplate.update("UPDATE instrument SET instrument_id = ? WHERE instrument_id = ?",
                instrument.getInstrumentId(), instrument.getInstrumentId());
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(Instrument instrument) {
        jdbcTemplate.update("INSERT INTO instrument (instrument_id) VALUES (?)", instrument.getInstrumentId());
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) {
        jdbcTemplate.update("DELETE FROM instrument WHERE instrument_id = ?", id);
        return false;
    }

    /**
     * Custom RowMapper implementation for mapping result sets to Instrument objects.
     */
    private class InstrumentRowMapper implements RowMapper<Instrument> {
        @Override
        public Instrument mapRow(ResultSet rs, int rowNum) throws SQLException {
            Instrument instrument = new Instrument(rs.getString("instrument_id"));
            return instrument;
        }
    }
}
