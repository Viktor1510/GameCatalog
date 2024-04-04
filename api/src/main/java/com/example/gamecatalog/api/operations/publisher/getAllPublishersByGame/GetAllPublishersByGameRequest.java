package com.example.gamecatalog.api.operations.publisher.getAllPublishersByGame;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllPublishersByGameRequest implements Request {
    private String gameId;
    private Integer startPage;
    private Integer size;
}
