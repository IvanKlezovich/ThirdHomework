package org.example.repository;

import org.example.dao.SingerDao;
import org.example.entities.Singer;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcSingerDao implements SingerDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcSingerDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcSingerDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcSingerDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String findById(long id) {

        try(var connection = dataSource.getConnection();
            var statement = connection.prepareStatement
                    ("SELECT * FROM singer WHERE id = " + id);
            var resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                return new Singer(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date"),
                        resultSet.getInt("version")).toString();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Singer singer) {
        return false;
    }

    @Override
    public boolean delete(Singer singer) {
        return false;
    }

    @Override
    public Iterable<Singer> findAll() {
        return jdbcTemplate
                .queryForList("select * from singer", Singer.class);
    }
}
