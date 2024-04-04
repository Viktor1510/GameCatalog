package com.example.gamecatalog.api.operations.tag.getAllTags;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.util.List;
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllTagsResponse implements Response {
    private List<GetTagItem> tags;
}
