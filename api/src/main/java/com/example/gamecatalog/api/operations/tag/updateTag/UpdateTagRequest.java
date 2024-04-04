package com.example.gamecatalog.api.operations.tag.updateTag;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateTagRequest implements Request {
 private String tagId;
 private String name;
}
