package org.example.repository;

import org.example.entities.Instrument;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InstrumentRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private RowMapper<Instrument> instrumentRowMapper;
    @Mock
    private ResultSet resultSet;

    private InstrumentRepository instrumentRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        instrumentRepository = new InstrumentRepository(jdbcTemplate);

        // Mock the behavior of the ResultSet
        when(resultSet.next()).thenReturn(true).thenReturn(false); // Simulate a single row
        when(resultSet.getString("instrument_id")).thenReturn("INSTRUMENT123");

        // Mock the behavior of the InstrumentRowMapper
        when(instrumentRowMapper.mapRow(any(ResultSet.class), anyInt())).thenAnswer(invocation -> {
            ResultSet rs = invocation.getArgument(0);
            return new Instrument(rs.getString("instrument_id"));
        });
    }

    @Test
    public void testFindAll() {
        // Arrange
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(List.of(new Instrument("INSTRUMENT123")));

        // Act
        List<Instrument> instruments = instrumentRepository.findAll();

        // Assert
        assertNotNull(instruments);
        assertFalse(instruments.isEmpty());
    }

    @Test
    public void testFindById() {
        // Arrange
        when(jdbcTemplate.queryForObject(anyString(), any(), any(RowMapper.class))).thenReturn(new Instrument("INSTRUMENT123"));

        // Act
        Instrument instrument = instrumentRepository.findById("INSTRUMENT123");

        // Assert
        assertNotNull(instrument);
        assertEquals("INSTRUMENT123", instrument.getInstrumentId());
    }

    @Test
    public void testUpdate() {
        // Arrange
        Instrument instrument = new Instrument("INSTRUMENT123");

        // Act
        boolean result = instrumentRepository.update(instrument);

        // Assert
        assertFalse(result); // Метод update возвращает false
        verify(jdbcTemplate).update(anyString(), (Object) any());
    }

    @Test
    public void testCreate() {
        // Arrange
        Instrument instrument = new Instrument("INSTRUMENT123");

        // Act
        boolean result = instrumentRepository.create(instrument);

        // Assert
        assertFalse(result); // Метод create возвращает false
        verify(jdbcTemplate).update(anyString(), (Object) any());
    }

    @Test
    public void testDelete() {
        // Arrange
        String id = "INSTRUMENT123";

        // Act
        boolean result = instrumentRepository.delete(id);

        // Assert
        assertFalse(result); // Метод delete возвращает false
        verify(jdbcTemplate).update(anyString(), (Object) any());
    }
}
