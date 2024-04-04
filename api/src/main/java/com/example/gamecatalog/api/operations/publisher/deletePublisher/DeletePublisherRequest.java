package com.example.gamecatalog.api.operations.publisher.deletePublisher;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeletePublisherRequest implements Request {
    private String publisherId;
}
