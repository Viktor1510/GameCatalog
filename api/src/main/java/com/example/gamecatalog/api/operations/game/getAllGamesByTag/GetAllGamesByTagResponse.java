package com.example.gamecatalog.api.operations.game.getAllGamesByTag;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGamesByTagResponse implements Response {
    List<GetAllGamesSingleItem> responses;
}
