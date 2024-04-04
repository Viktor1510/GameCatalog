package com.example.gamecatalog.api.operations.multimedia.getMultimedia;

import com.example.gamecatalog.api.base.Response;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMultimediaResponse implements Response {
       private String url;
}
