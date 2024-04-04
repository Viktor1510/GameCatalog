package com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGamesByMultimediaRequest implements Request {
    private String multimediaId;
    private Integer size;
    private Integer startPage;
}
