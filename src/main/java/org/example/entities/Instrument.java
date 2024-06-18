package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "instrument")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrument implements Serializable {
    private static final long serialVersionUID = 1L;

    public Instrument(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    @Id
    @Column(name = "instrument_id")
    private String instrumentId;

    @ManyToMany
    @JoinTable(name = "singer_instrument",
        joinColumns = @JoinColumn(name = "instrument_id"),
        inverseJoinColumns = @JoinColumn(name = "singer_id"))
    private Set<Singer> singers;
}
