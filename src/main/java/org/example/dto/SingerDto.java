package org.example.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.Album;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SingerDto {
    private String firstname;

    private String lastname;

    private Date birthDate;

    private Set<Album> albums;
}
