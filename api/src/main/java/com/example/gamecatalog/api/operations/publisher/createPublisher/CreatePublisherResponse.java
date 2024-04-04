package com.example.gamecatalog.api.operations.publisher.createPublisher;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePublisherResponse implements Response {
    private String name;
    private String address;
    private LocalDate dateOfCreation;
    private String[] games;
}
