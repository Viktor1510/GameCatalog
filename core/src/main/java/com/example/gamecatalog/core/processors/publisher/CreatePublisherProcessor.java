package com.example.gamecatalog.core.processors.publisher;

import com.example.gamecatalog.api.operations.publisher.createPublisher.CreatePublisherOperation;
import com.example.gamecatalog.api.operations.publisher.createPublisher.CreatePublisherRequest;
import com.example.gamecatalog.api.operations.publisher.createPublisher.CreatePublisherResponse;
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
public class CreatePublisherProcessor implements CreatePublisherOperation {
    private final PublisherRepository publisherRepository;
    private final GameRepository gameRepository;
    private final Logger logger;
    @Override
    public CreatePublisherResponse process(CreatePublisherRequest request) {
       try {
           Publisher publisher = Publisher.builder()
                   .name(request.getName())
                   .address(request.getAddress())
                   .dateOfCreation(request.getDateOfCreation())
                   .games(getAllGameIds(request.getGames()))
                   .build();
       logger.info("Created publisher with id {}",publisher.getPublisherId());
       return CreatePublisherResponse.builder()
               .name(publisher.getName())
               .address(publisher.getAddress())
               .dateOfCreation(publisher.getDateOfCreation())
               .games(publisher.getGames().toArray(String[]::new))
               .build();
       }
       catch (Exception e)
       {
        logger.error("Error creating publisher!");
        throw new RuntimeException("Error creating publisher!");
       }

    }

    private Set<Game> getAllGameIds(String[] tagIds) {
        return Arrays.stream(tagIds)
                .map(id -> gameRepository.findById(UUID.fromString(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
