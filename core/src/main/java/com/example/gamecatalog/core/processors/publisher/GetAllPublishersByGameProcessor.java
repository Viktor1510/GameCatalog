package com.example.gamecatalog.core.processors.publisher;

import com.example.gamecatalog.api.operations.publisher.getAllPublishersByGame.GetAllPublishersByGameOperation;
import com.example.gamecatalog.api.operations.publisher.getAllPublishersByGame.GetAllPublishersByGameRequest;
import com.example.gamecatalog.api.operations.publisher.getAllPublishersByGame.GetAllPublishersByGameResponse;
import com.example.gamecatalog.api.operations.publisher.getAllPublishersByGame.PublisherByGameItem;
import com.example.gamecatalog.core.exceptions.game.GameNotFoundException;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Publisher;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import com.example.gamecatalog.persistence.repositories.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllPublishersByGameProcessor implements GetAllPublishersByGameOperation {
    private final Logger logger;
    private final GameRepository gameRepository;
    private final PublisherRepository publisherRepository;


 @Override
 public GetAllPublishersByGameResponse process(GetAllPublishersByGameRequest request) {
  try {
   Game game=gameRepository.findById(UUID.fromString(request.getGameId()))
           .orElseThrow(()->new GameNotFoundException("Game not found!"));
   org.springframework.data.domain.Pageable pageable = (org.springframework.data.domain.Pageable) PageRequest.of(request.getStartPage(), request.getSize(),
           Sort.by("name").ascending());
   Page<Publisher> publisher = publisherRepository.findByGamesIn(Collections.singleton((Set<Game>) game), pageable);

   List<PublisherByGameItem> publisherResponses = publisher.getContent().stream()
           .map(this::mapGameToSingleItem)
           .toList();
   logger.info("Retrieved publishers by game successfully!");

   return GetAllPublishersByGameResponse.builder()
           .responses(publisherResponses)
           .build();
  } catch (Exception e) {
   logger.error("Error retrieving publishers by game!");
   throw new RuntimeException("Error retrieving publishers by game!");
  }
 }

 private PublisherByGameItem mapGameToSingleItem(Publisher publisher) {
  return PublisherByGameItem.builder()
          .games(publisher.getGames().toArray(String[]::new))
          .name(publisher.getName())
          .address(publisher.getAddress())
          .dateOfCreation(publisher.getDateOfCreation())
          .build()
          ;
 }


}
