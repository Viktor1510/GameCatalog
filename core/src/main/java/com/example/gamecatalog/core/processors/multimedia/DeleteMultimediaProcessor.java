package com.example.gamecatalog.core.processors.multimedia;

import com.example.gamecatalog.api.operations.multimedia.deleteMultimedia.DeleteMultimediaOperation;
import com.example.gamecatalog.api.operations.multimedia.deleteMultimedia.DeleteMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.deleteMultimedia.DeleteMultimediaResponse;
import com.example.gamecatalog.api.operations.tag.deleteTag.DeleteTagResponse;
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
public class DeleteMultimediaProcessor implements DeleteMultimediaOperation {
   private final Logger logger;
   private final MultimediaRepository multimediaRepository;

    @Override
    public DeleteMultimediaResponse process(DeleteMultimediaRequest request) {
        try {
            Optional<Multimedia> multimediaOptional = Optional.ofNullable(multimediaRepository.findById(UUID.fromString(request.getMultimediaId()))
                    .orElseThrow(() -> new MultimediaNotFoundException("Multimedia not found!")));
            Multimedia multimedia = multimediaOptional.get();
            multimediaRepository.delete(multimedia);
            logger.info("Multimedia deleted successfully. Multimedia Id: {}", multimedia.getMultimediaId());
            return DeleteMultimediaResponse.builder()
                    .url(multimedia.getUrl())
                    .build();
        } catch (TagNotFoundException e) {
            logger.error("Error deleting multimedia. Multimedia not found.", e);
            throw e;

    }
    }
}
