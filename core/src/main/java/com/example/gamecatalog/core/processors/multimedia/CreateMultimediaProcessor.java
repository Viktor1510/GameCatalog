package com.example.gamecatalog.core.processors.multimedia;

import com.example.gamecatalog.api.operations.multimedia.createMultimedia.CreateMultimediaOperation;
import com.example.gamecatalog.api.operations.multimedia.createMultimedia.CreateMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.createMultimedia.CreateMultimediaResponse;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMultimediaProcessor implements CreateMultimediaOperation {
    private final Logger logger;
    private final MultimediaRepository multimediaRepository;

    @Override
    public CreateMultimediaResponse process(CreateMultimediaRequest request) {
        try {
            Multimedia multimedia = Multimedia.builder().url(request.getUrl()).build();
            Multimedia persisted = multimediaRepository.save(multimedia);
            logger.info("Multimedia created successfully with ID: {}", persisted.getMultimediaId());
            return CreateMultimediaResponse.builder()
                    .url(persisted.getUrl())
                    .build();
        } catch (Exception e) {
            logger.error("Error occurred while processing CreateMultimediaRequest: {}", e.getMessage());
            throw new RuntimeException("Failed to create multimedia", e);
        }
    }
}
