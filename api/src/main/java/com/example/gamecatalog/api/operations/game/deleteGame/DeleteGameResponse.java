package com.example.gamecatalog.api.operations.game.deleteGame;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteGameResponse implements Response {
    private String name;
    private String description;
    private String story;
    private String[] multimedia;
    private String[] tags;
}
