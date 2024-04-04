package com.example.gamecatalog.api.operations.publisher.getAllPublishersByGame;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllPublishersByGameResponse implements Response {
    private List<PublisherByGameItem> responses;
}
