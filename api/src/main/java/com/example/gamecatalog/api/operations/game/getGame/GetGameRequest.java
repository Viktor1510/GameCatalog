package com.example.gamecatalog.api.operations.game.getGame;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGameRequest implements Request {
    private String gameId;
}
