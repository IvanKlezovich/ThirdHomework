package org.example.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * The {@code Author} class represents an author with various attributes such as ID, first name, last name, autobiography,
 * and date of birth. This class is annotated with Lombok annotations to automatically generate common boilerplate code.
 *
 * @author Klezovich Ivan
 * @version 1.0
 * @since 22
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "author")
public class Author implements Serializable {

    /**
     * SerialVersionUID for serialization.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the author.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The first name of the author.
     */
    private String firstName;

    /**
     * The last name of the author.
     */
    private String lastName;

    /**
     * The autobiography written by the author.
     */
    private String autobiography;

    /**
     * The date of birth of the author.
     */
    private Date dateOfBirth;
}
