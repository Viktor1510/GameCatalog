package com.example.gamecatalog.core.processors.publisher;

import com.example.gamecatalog.api.operations.publisher.updatePublisher.UpdatePublisherOperation;
import com.example.gamecatalog.api.operations.publisher.updatePublisher.UpdatePublisherRequest;
import com.example.gamecatalog.api.operations.publisher.updatePublisher.UpdatePublisherResponse;
import com.example.gamecatalog.core.exceptions.publisher.PublisherNotFoundException;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Publisher;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import com.example.gamecatalog.persistence.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdatePublisherProcessor implements UpdatePublisherOperation {
    private final PublisherRepository publisherRepository;
    private final GameRepository gameRepository;
    private final Logger logger;

    @Override
    public UpdatePublisherResponse process(UpdatePublisherRequest request) {
        try {
            Optional<Publisher> publisherOptional = Optional.ofNullable(this.publisherRepository.findById(UUID.fromString(request.getPublisherId()))
                    .orElseThrow(() -> new PublisherNotFoundException("Publisher  not found!")));
            Publisher publisher= Publisher.builder()
                    .publisherId(publisherOptional.get().getPublisherId())
                    .name(request.getName())
                    .dateOfCreation(request.getDateOfCreation())
                    .address(request.getAddress())
                    .games(getAllGameIds(request.getGames()))
                    .build();
            logger.info("Publisher with id {} was updated!",publisher.getPublisherId());
            return UpdatePublisherResponse.builder()
                    .name(publisher.getName())
                    .address(publisher.getAddress())
                    .dateOfCreation(publisher.getDateOfCreation())
                    .games(publisher.getGames().toArray(String[]::new))
                    .build();
        }
        catch (Exception e)
        {
            logger.error("Error updating publisher!");
            throw new RuntimeException("Error updating publisher!");
        }
    }

    private Set<Game> getAllGameIds(String[] gameIds) {
        return Arrays.stream(gameIds)
                .map(id ->gameRepository.findById(UUID.fromString(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
