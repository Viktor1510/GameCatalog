package com.example.gamecatalog.api.operations.tag.createatag;

import com.example.gamecatalog.api.base.Request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTagRequest implements Request {
    @NotEmpty
    @Size(min=5, max =15)
    private String name;

}
