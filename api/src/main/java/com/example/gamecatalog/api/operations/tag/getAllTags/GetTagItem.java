package com.example.gamecatalog.api.operations.tag.getAllTags;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTagItem {
    private String tagId;
    private String name;
}
