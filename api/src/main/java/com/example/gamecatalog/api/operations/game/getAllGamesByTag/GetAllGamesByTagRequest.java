package com.example.gamecatalog.api.operations.game.getAllGamesByTag;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGamesByTagRequest implements Request {
    private String tagId;
    private Integer size;
    private Integer startPage;
}
