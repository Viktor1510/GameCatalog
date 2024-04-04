package com.example.gamecatalog.api.operations.game.getAllGames;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGamesResponse implements Response {
    List<GetGameItem> games;
}
