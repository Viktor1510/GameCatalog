package com.example.gamecatalog.api.operations.publisher.getAllPublishersByGame;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherByGameItem {
    private String name;
    private String address;
    private LocalDate dateOfCreation;
    private String[] games;
}
