package org.example.service;

import org.example.dto.SingerDto;
import org.example.entities.Singer;
import org.example.repository.SingerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SingerServiceTest {

    @Mock
    private SingerRepository singerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SingerService singerService;

    @Test
    public void testGetAllSingers() {
        // Arrange
        Singer singer1 = new Singer();
        Singer singer2 = new Singer();
        when(singerRepository.findAll()).thenReturn(Arrays.asList(singer1, singer2));
        when(modelMapper.map(singer1, SingerDto.class)).thenReturn(new SingerDto());
        when(modelMapper.map(singer2, SingerDto.class)).thenReturn(new SingerDto());

        // Act
        List<SingerDto> result = singerService.getAllSingers();

        // Assert
        assertEquals(2, result.size());
        verify(singerRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(Singer.class), eq(SingerDto.class));
    }

    @Test
    public void testGetSingerById() {
        // Arrange
        Long id = 1L;
        Singer singer = new Singer();
        when(singerRepository.findById(id)).thenReturn(singer);
        when(modelMapper.map(singer, SingerDto.class)).thenReturn(new SingerDto());

        // Act
        SingerDto result = singerService.getSingerById(id);

        // Assert
        verify(singerRepository, times(1)).findById(id);
        verify(modelMapper, times(1)).map(singer, SingerDto.class);
    }

    @Test
    public void testCreateSinger() {
        // Arrange
        SingerDto singerDto = new SingerDto();
        Singer singer = new Singer();
        when(modelMapper.map(singerDto, Singer.class)).thenReturn(singer);
        when(singerRepository.create(singer)).thenReturn(true);

        // Act
        boolean result = singerService.createSinger(singerDto);

        // Assert
        assertTrue(result);
        verify(modelMapper, times(1)).map(singerDto, Singer.class);
        verify(singerRepository, times(1)).create(singer);
    }

    @Test
    public void testUpdateSinger() {
        // Arrange
        Long id = 1L;
        SingerDto singerDto = new SingerDto();
        Singer singer = new Singer();
        when(modelMapper.map(singerDto, Singer.class)).thenReturn(singer);
        when(singerRepository.update(singer)).thenReturn(true);

        // Act
        boolean result = singerService.updateSinger(id, singerDto);

        // Assert
        assertTrue(result);
        verify(modelMapper, times(1)).map(singerDto, Singer.class);
        verify(singerRepository, times(1)).update(singer);
    }

    @Test
    public void testDeleteSinger() {
        // Arrange
        Long id = 1L;
        when(singerRepository.delete(id)).thenReturn(true);

        // Act
        boolean result = singerService.deleteSinger(id);

        // Assert
        assertTrue(result);
        verify(singerRepository, times(1)).delete(id);
    }
}