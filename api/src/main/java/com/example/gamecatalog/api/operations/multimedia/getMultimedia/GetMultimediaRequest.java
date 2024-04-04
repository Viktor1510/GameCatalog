package com.example.gamecatalog.api.operations.multimedia.getMultimedia;

import com.example.gamecatalog.api.base.Request;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMultimediaRequest implements Request {
    private String multimediaId;

}
