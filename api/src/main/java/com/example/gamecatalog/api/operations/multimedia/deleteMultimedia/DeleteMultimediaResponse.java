package com.example.gamecatalog.api.operations.multimedia.deleteMultimedia;

import com.example.gamecatalog.api.base.Response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteMultimediaResponse implements Response {
    private String url;
}
