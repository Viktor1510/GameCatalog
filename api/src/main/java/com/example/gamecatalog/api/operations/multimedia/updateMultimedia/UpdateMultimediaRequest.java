package com.example.gamecatalog.api.operations.multimedia.updateMultimedia;
import com.example.gamecatalog.api.base.Request;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMultimediaRequest implements Request {
    private String multimediaId;
    private String url;
}
