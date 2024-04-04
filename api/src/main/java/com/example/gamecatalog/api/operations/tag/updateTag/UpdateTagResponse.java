package com.example.gamecatalog.api.operations.tag.updateTag;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
public class UpdateTagResponse implements Response {
    private String name;
}
