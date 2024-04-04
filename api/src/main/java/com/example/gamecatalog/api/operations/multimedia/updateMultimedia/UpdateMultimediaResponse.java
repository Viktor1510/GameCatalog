package com.example.gamecatalog.api.operations.multimedia.updateMultimedia;

import com.example.gamecatalog.api.base.Response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMultimediaResponse implements Response {
    private String url;
}
