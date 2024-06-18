package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Singer;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto {

    private int id;

    private String title;

    private Date releaseDate;

    private Singer singer;
}
