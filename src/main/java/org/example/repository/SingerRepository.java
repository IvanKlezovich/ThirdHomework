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

@Repository
public class SingerRepository implements SingerDao {

    private final JdbcTemplate jdbcTemplate;

    public SingerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Singer> findAll() {
        return jdbcTemplate.query("SELECT * FROM singer", new SingerRowMapper());
    }

    @Override
    public Singer findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM singer WHERE id = ?", new Object[]{id}, new SingerRowMapper());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean update(Singer singer) {
        try {
            jdbcTemplate.update("UPDATE singer SET first_name = ?, last_name = ?, birth_date = ?, version = ? WHERE id = ?",
                    singer.getFirstname(), singer.getLastname(), singer.getBirthDate(), singer.getVersion(), singer.getId());
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public boolean create(Singer singer) {
        try {
            jdbcTemplate.update("INSERT INTO singer (first_name, last_name, birth_date, version) VALUES (?, ?, ?, ?)",
                    singer.getFirstname(), singer.getLastname(), singer.getBirthDate(), singer.getVersion());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try{
            jdbcTemplate.update("DELETE FROM singer WHERE id = ?", id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

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
