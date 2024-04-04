package com.example.gamecatalog.api.operations.publisher.updatePublisher;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePublisherResponse implements Response {
    private String name;
    private String address;
    private LocalDate dateOfCreation;
    private String[] games;
}
