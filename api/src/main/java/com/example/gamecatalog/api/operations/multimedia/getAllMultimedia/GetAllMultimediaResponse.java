package com.example.gamecatalog.api.operations.multimedia.getAllMultimedia;

import com.example.gamecatalog.api.base.Response;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllMultimediaResponse implements Response {
    private List<GetMultimediaItem> multimedia;
}
