package com.example.gamecatalog.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Publisher")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID publisherId;
    private String name;
    private String address;
    private LocalDate dateOfCreation;
    @OneToMany
    private Set<Game>games;
}
