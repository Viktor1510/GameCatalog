package com.example.gamecatalog.api.operations.game.deleteGame;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteGameRequest implements Request {
    private String gameId;
}
