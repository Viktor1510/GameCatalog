package com.example.gamecatalog.core.processors.multimedia;

import com.example.gamecatalog.api.operations.multimedia.getMultimedia.GetMultimediaOperation;
import com.example.gamecatalog.api.operations.multimedia.getMultimedia.GetMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.getMultimedia.GetMultimediaResponse;
import com.example.gamecatalog.api.operations.tag.getTag.GetTagResponse;
import com.example.gamecatalog.core.exceptions.multimedia.MultimediaNotFoundException;
import com.example.gamecatalog.core.exceptions.tag.TagNotFoundException;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetMultimediaProcessor implements GetMultimediaOperation {
    private final Logger logger;
    private final MultimediaRepository multimediaRepository;
    @Override
    public GetMultimediaResponse process(GetMultimediaRequest request) {
        try {
            Optional<Multimedia> multimedia = java.util.Optional.ofNullable(multimediaRepository.findById(UUID.fromString(request.getMultimediaId()))
                    .orElseThrow(() -> new MultimediaNotFoundException("Multimedia not found!")));
            logger.info("Multimedia retrieved successfully. TagId: {}", request.getMultimediaId());
            return GetMultimediaResponse.builder()
                    .url(multimedia.get().getUrl())
                    .build();
        } catch (Exception e) {
            logger.error("Error retrieving tag. TagId: {}", request.getMultimediaId(), e);
            throw new RuntimeException("Error retrieving tag. TagId: " + request.getMultimediaId(), e);
        }

    }
}
