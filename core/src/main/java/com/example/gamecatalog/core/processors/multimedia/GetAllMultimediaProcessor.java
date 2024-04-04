package com.example.gamecatalog.core.processors.multimedia;

import com.example.gamecatalog.api.operations.multimedia.getAllMultimedia.GetAllMultimediaOperation;
import com.example.gamecatalog.api.operations.multimedia.getAllMultimedia.GetAllMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.getAllMultimedia.GetAllMultimediaResponse;
import com.example.gamecatalog.api.operations.multimedia.getAllMultimedia.GetMultimediaItem;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetAllTagsResponse;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetTagItem;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GetAllMultimediaProcessor implements GetAllMultimediaOperation {
 private final Logger logger;
 private final MultimediaRepository multimediaRepository;

    @Override
    public GetAllMultimediaResponse process(GetAllMultimediaRequest request) {
        try {

            List<GetMultimediaItem> multimediaResponses = multimediaRepository.findAll()
                    .stream()
                    .map(this::mapMultimediaToSingleItem)
                    .toList();

            logger.info("Retrieved all multimedia successfully.");

            return GetAllMultimediaResponse.builder()
                    .multimedia(multimediaResponses)
                    .build();
        } catch (Exception e) {
            logger.error("Error retrieving all multimedia.", e);
            throw new RuntimeException("Error retrieving all multimedia.", e);
        }
    }

    private GetMultimediaItem mapMultimediaToSingleItem(Multimedia multimedia) {
        return GetMultimediaItem.builder()
                .url(multimedia.getUrl())
                .build();
    }
}
