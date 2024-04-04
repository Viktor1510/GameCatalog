package com.example.gamecatalog.api.operations.publisher.getAllPublishers;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPublisherItem {
    private String name;
    private String address;
    private LocalDate dateOfCreation;
    private String[] games;
}
