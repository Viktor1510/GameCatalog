package com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGamesByMultimediaResponse implements Response {
    private List<GetGameByMultimedia> responses;
}
