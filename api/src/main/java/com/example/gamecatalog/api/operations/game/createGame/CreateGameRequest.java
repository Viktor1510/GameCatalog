package com.example.gamecatalog.api.operations.game.createGame;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameRequest implements Request {
    private String name;
    private String description;
    private String story;
    private String[] multimedia;
    private String[] tags;
}
