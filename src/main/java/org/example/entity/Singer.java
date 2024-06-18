package org.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "singer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Singer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Version
    @Column(name = "version")
    private Integer version;

    @Column(name = "last_name")
    private String lastname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    public Singer(Long id, String firstname, String lastname,
                  Date birthDate, int version) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.version = version;
    }

    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Album> albums;

    @ManyToMany
    @JoinTable(name = "singer_instrument",
            joinColumns = @JoinColumn(name = "singer_id"),
            inverseJoinColumns = @JoinColumn(name = "instrument_id"))
    private Set<Instrument> instruments;
}