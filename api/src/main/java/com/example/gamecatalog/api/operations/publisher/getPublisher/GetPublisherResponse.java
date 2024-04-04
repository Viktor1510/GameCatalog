package com.example.gamecatalog.api.operations.publisher.getPublisher;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPublisherResponse implements Response {
    private String name;
    private String address;
    private LocalDate dateOfCreation;
    private String[] games;
}
