package com.example.gamecatalog.core.processors.publisher;

import com.example.gamecatalog.api.operations.publisher.deletePublisher.DeletePublisherOperation;
import com.example.gamecatalog.api.operations.publisher.deletePublisher.DeletePublisherRequest;
import com.example.gamecatalog.api.operations.publisher.deletePublisher.DeletePublisherResponse;
import com.example.gamecatalog.core.exceptions.publisher.PublisherNotFoundException;
import com.example.gamecatalog.persistence.entities.Publisher;
import com.example.gamecatalog.persistence.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeletePublisherProcessor implements DeletePublisherOperation {
    private final PublisherRepository publisherRepository;
    private final Logger logger;
    @Override
    public DeletePublisherResponse process(DeletePublisherRequest request) {
        try {
            Optional<Publisher> publisherOptional = Optional.ofNullable(this.publisherRepository.findById(UUID.fromString(request.getPublisherId()))
                    .orElseThrow(() -> new PublisherNotFoundException("Publisher  not found!")));
            Publisher publisher = publisherOptional.get();
            this.publisherRepository.delete(publisher);
            logger.info("Publisher with id {} was deleted!", publisher.getPublisherId());
            return DeletePublisherResponse.builder().build();
        }
        catch (Exception e)
        {
            logger.error("Error finding publisher!");
            throw new RuntimeException("Error finding publisher!");
        }
    }
}
