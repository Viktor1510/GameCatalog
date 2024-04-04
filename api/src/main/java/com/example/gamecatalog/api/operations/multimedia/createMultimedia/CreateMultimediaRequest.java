package com.example.gamecatalog.api.operations.multimedia.createMultimedia;

import com.example.gamecatalog.api.base.Request;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMultimediaRequest implements Request {
    private String url;
}
