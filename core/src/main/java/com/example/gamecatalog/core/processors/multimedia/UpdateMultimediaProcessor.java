package com.example.gamecatalog.core.processors.multimedia;

import com.example.gamecatalog.api.operations.multimedia.updateMultimedia.UpdateMultimediaOperation;
import com.example.gamecatalog.api.operations.multimedia.updateMultimedia.UpdateMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.updateMultimedia.UpdateMultimediaResponse;
import com.example.gamecatalog.api.operations.tag.updateTag.UpdateTagResponse;
import com.example.gamecatalog.core.exceptions.multimedia.MultimediaNotFoundException;
import com.example.gamecatalog.core.exceptions.tag.TagNotFoundException;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateMultimediaProcessor implements UpdateMultimediaOperation {
    private final Logger logger;
    private final MultimediaRepository multimediaRepository;
    @Override
    public UpdateMultimediaResponse process(UpdateMultimediaRequest request) {
        try {
            Optional<Multimedia> multimediaOptional = Optional.ofNullable(multimediaRepository.findById(UUID.fromString(request.getMultimediaId()))
                    .orElseThrow(() -> new MultimediaNotFoundException("Multimedia not found!")));
            Multimedia tag = multimediaOptional.get();
            tag.setUrl(request.getUrl());
            multimediaRepository.save(tag);

            logger.info("Tag updated successfully. TagId: {}, New url: {}", request.getMultimediaId(), request.getUrl());

            return UpdateMultimediaResponse.builder()
                    .url(tag.getUrl())
                    .build();
        } catch (Exception e) {
            logger.error("Error updating multimedia. Multimedia Id: {}, New url: {}", request.getMultimediaId(), request.getUrl(), e);
            throw new RuntimeException("Error updating multimedia. Multimedia Id: " + request.getMultimediaId(), e);
        }
    }
}
