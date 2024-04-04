package com.example.gamecatalog.api.operations.tag.getTag;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class GetTagRequest implements Request {
    private String tagId;

}
