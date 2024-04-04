package com.example.gamecatalog.core.processors.publisher;

import com.example.gamecatalog.api.operations.publisher.getPublisher.GetPublisherOperation;
import com.example.gamecatalog.api.operations.publisher.getPublisher.GetPublisherRequest;
import com.example.gamecatalog.api.operations.publisher.getPublisher.GetPublisherResponse;
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
public class GetPublisherProcessor implements GetPublisherOperation {
    private final PublisherRepository publisherRepository;
    private final Logger logger;
    @Override
    public GetPublisherResponse process(GetPublisherRequest request) {
        try {
            Optional<Publisher> publisherOptional = Optional.ofNullable(this.publisherRepository.findById(UUID.fromString(request.getPublisherId()))
                    .orElseThrow(() -> new PublisherNotFoundException("Publisher  not found!")));
            Publisher publisher = publisherOptional.get();
            logger.info("Publisher with id {} was found!", publisher.getPublisherId());
            return GetPublisherResponse.builder()
                    .name(publisher.getName())
                    .address(publisher.getAddress())
                    .dateOfCreation(publisher.getDateOfCreation())
                    .games(publisher.getGames().toArray(String[]::new))
                    .build();
        }
        catch (Exception e)
        {
            logger.error("Error finding publisher!");
            throw new RuntimeException("Error finding publisher!");
        }
    }
}
