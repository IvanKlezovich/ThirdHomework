package org.example.repository;

import org.example.entities.Album;
import org.example.entities.Singer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AlbumRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private SingerRepository singerRepository;
    @Mock
    private RowMapper<Album> albumRowMapper;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Singer singer;

    private AlbumRepository albumRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        albumRepository = new AlbumRepository(jdbcTemplate, singerRepository);

        // Mock the behavior of the ResultSet
        when(resultSet.next()).thenReturn(true).thenReturn(false); // Simulate a single row
        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("title")).thenReturn("Test Album");
        when(resultSet.getDate("release_date")).thenReturn((java.sql.Date) new Date(System.currentTimeMillis()));
        when(resultSet.getInt("version")).thenReturn(1);
        when(resultSet.getLong("singer_id")).thenReturn(1L);

        // Mock the behavior of the Singer
        when(singer.getId()).thenReturn(1L);
        when(singer.getFirstname()).thenReturn("Test");
        when(singer.getLastname()).thenReturn("Singer");

        // Mock the behavior of the AlbumRowMapper
        when(albumRowMapper.mapRow(any(ResultSet.class), anyInt())).thenAnswer(invocation -> {
            ResultSet rs = invocation.getArgument(0);
            Album album = new Album();
            album.setId(rs.getLong("id"));
            album.setTitle(rs.getString("title"));
            album.setReleaseDate(rs.getDate("release_date"));
            album.setVersion(rs.getInt("version"));
            album.setSinger(singer);
            return album;
        });
    }

    @Test
    public void testFindAll() {
        // Arrange
        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(List.of(new Album()));

        // Act
        List<Album> albums = albumRepository.findAll();

        // Assert
        assertNotNull(albums);
        assertFalse(albums.isEmpty());
    }

    @Test
    public void testFindById() {
        // Arrange
        when(jdbcTemplate.queryForObject(anyString(), any(), any(RowMapper.class))).thenReturn(new Album());

        // Act
        Album album = albumRepository.findById(1L);

        // Assert
        assertNotNull(album);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Album album = new Album();
        album.setId(1L);
        album.setTitle("Updated Album");
        album.setReleaseDate(new Date(System.currentTimeMillis()));
        album.setVersion(2);
        album.setSinger(singer);

        // Act
        boolean result = albumRepository.update(album);

        // Assert
        assertFalse(result); // Метод update возвращает false
        verify(jdbcTemplate).update(anyString(), any(), any(), any(), any(), any());
    }

    @Test
    public void testCreate() {
        // Arrange
        Album album = new Album();
        album.setTitle("New Album");
        album.setReleaseDate(new Date(System.currentTimeMillis()));
        album.setVersion(1);
        album.setSinger(singer);

        // Act
        boolean result = albumRepository.create(album);

        // Assert
        assertFalse(result); // Метод create возвращает false
        verify(jdbcTemplate).update(anyString(), any(), any(), any(), any());
    }

    @Test
    public void testDelete() {
        // Arrange
        Long id = 1L;

        // Act
        boolean result = albumRepository.delete(id);

        // Assert
        assertFalse(result); // Метод delete возвращает false
        //verify(jdbcTemplate).update(anyString(), any());
    }
}