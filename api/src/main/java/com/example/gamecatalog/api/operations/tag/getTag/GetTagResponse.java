package com.example.gamecatalog.api.operations.tag.getTag;

import com.example.gamecatalog.api.base.Response;
import lombok.*;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTagResponse implements Response {
   private String name;
}
