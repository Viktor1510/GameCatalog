package com.example.gamecatalog.api.operations.multimedia.deleteMultimedia;

import com.example.gamecatalog.api.base.Request;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteMultimediaRequest implements Request {
    private String multimediaId;
}
