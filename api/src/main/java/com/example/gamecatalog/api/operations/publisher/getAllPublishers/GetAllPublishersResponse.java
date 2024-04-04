package com.example.gamecatalog.api.operations.publisher.getAllPublishers;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPublishersResponse implements Response {
    private List<GetPublisherItem> publishers;
}
