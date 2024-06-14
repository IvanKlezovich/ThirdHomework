package org.example.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String title;
    private String author;
    private String publisher;
    private String price;
}
