package com.example.gamecatalog.api.operations.game.getAllGamesByTag;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGamesSingleItem {
    private String name;
    private String story;
    private String description;
    private String[] multimedia;
    private String[] tags;
}
