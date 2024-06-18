package org.example.repository;

import jakarta.servlet.annotation.WebServlet;
import org.example.config.DataBaseConfig;
import org.example.config.SpringConfig;
import org.example.entities.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, DataBaseConfig.class}) // Укажите ваш конфигурационный класс для тестов
public class SingerRepositoryTest {

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testFindAll() {
        // Arrange
        Singer singer = new Singer(1L, "John", "Doe", new Date(System.currentTimeMillis()), 1);
        jdbcTemplate.update("INSERT INTO singer (id, first_name, last_name, birth_date, version) VALUES (?, ?, ?, ?, ?)",
                singer.getId(), singer.getFirstname(), singer.getLastname(), singer.getBirthDate(), singer.getVersion());

        // Act
        List<Singer> singers = singerRepository.findAll();

        // Assert
        assertNotNull(singers);
        assertEquals(1, singers.size());
        assertEquals("John", singers.get(0).getFirstname());
        assertEquals("Doe", singers.get(0).getLastname());
    }

    @Test
    public void testFindById() {
        // Arrange
        Singer singer = new Singer(1L, "John", "Doe", new Date(System.currentTimeMillis()), 1);
        jdbcTemplate.update("INSERT INTO singer (id, first_name, last_name, birth_date, version) VALUES (?, ?, ?, ?, ?)",
                singer.getId(), singer.getFirstname(), singer.getLastname(), singer.getBirthDate(), singer.getVersion());

        // Act
        Singer foundSinger = singerRepository.findById(1L);

        // Assert
        assertNotNull(foundSinger);
        assertEquals("John", foundSinger.getFirstname());
        assertEquals("Doe", foundSinger.getLastname());
    }

    // Добавьте остальные тесты для методов update, create и delete аналогичным образом
}