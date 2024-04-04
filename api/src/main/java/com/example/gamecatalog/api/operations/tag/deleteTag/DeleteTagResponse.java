package com.example.gamecatalog.api.operations.tag.deleteTag;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public class DeleteTagResponse implements Response {
    private String name;
}
