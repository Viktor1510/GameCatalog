package com.example.gamecatalog.api.operations.game.updateGame;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGameRequest implements Request {
    private String gameId;
    private String name;
    private String story;
    private String description;
    private String[] multimedia;
    private String[] tags;
}
