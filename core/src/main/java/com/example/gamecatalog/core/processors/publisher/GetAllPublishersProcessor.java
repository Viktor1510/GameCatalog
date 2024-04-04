package com.example.gamecatalog.core.processors.publisher;

import com.example.gamecatalog.api.operations.game.getAllGames.GetGameItem;
import com.example.gamecatalog.api.operations.publisher.getAllPublishers.GetAllPublishersOperation;
import com.example.gamecatalog.api.operations.publisher.getAllPublishers.GetAllPublishersRequest;
import com.example.gamecatalog.api.operations.publisher.getAllPublishers.GetAllPublishersResponse;
import com.example.gamecatalog.api.operations.publisher.getAllPublishers.GetPublisherItem;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Publisher;
import com.example.gamecatalog.persistence.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetAllPublishersProcessor implements GetAllPublishersOperation {
    private final PublisherRepository publisherRepository;
    private final Logger logger;

    @Override
    public GetAllPublishersResponse process(GetAllPublishersRequest request) {
        try {
            List<GetPublisherItem> publisherResponses = publisherRepository.findAll()
                    .stream()
                    .map(this::mapPublisherToSingleItem)
                    .toList();
            logger.info("Retrieved all publishers successfully!");
            return GetAllPublishersResponse.builder().build();
        } catch (Exception e) {
          logger.error("Error retrieving all publishers!");
          throw new RuntimeException("Error retrieving all publishers!");
        }
    }

    private GetPublisherItem mapPublisherToSingleItem(Publisher publisher){
       return GetPublisherItem.builder()
               .name(publisher.getName())
               .address(publisher.getAddress())
               .dateOfCreation(publisher.getDateOfCreation())
               .games(publisher.getGames().toArray(String[]::new))
               .build();
    }
}
