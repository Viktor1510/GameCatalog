package com.example.gamecatalog.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID multimediaId;
    private String url;
    @ManyToOne
    private Game game;
}