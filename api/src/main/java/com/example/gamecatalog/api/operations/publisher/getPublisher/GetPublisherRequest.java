package com.example.gamecatalog.api.operations.publisher.getPublisher;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPublisherRequest implements Request {
    private String publisherId;
}
