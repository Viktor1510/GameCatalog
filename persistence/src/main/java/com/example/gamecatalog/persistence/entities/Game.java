package com.example.gamecatalog.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@Table(name="Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID gameId;
    private String name;
    private String description;
    private String story;
    @ManyToMany
    private Set<Tag> tags;
    @ManyToOne
    private Publisher publisher;
    @OneToMany
    private Set<Multimedia> multimedia;
}
