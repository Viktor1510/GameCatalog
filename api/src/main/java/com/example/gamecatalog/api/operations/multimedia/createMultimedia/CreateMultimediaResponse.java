package com.example.gamecatalog.api.operations.multimedia.createMultimedia;

import com.example.gamecatalog.api.base.Response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMultimediaResponse implements Response {
    private String url;
}
