package com.example.gamecatalog.api.operations.game.getAllGames;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetGameItem {
    private String name;
    private String story;
    private String description;
    private String[] multimedia;
    private String[] tags;
}
