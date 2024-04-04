package com.example.gamecatalog.api.operations.tag.deleteTag;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteTagRequest implements Request {
    private String tagId;
}
