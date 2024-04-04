package com.example.gamecatalog.api.operations.tag.createatag;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagResponse implements Response {
    private String name;
}
