package com.example.gamecatalog.core.processors.game;

import com.example.gamecatalog.api.operations.game.getAllGames.GetAllGamesOperation;
import com.example.gamecatalog.api.operations.game.getAllGames.GetAllGamesRequest;
import com.example.gamecatalog.api.operations.game.getAllGames.GetAllGamesResponse;
import com.example.gamecatalog.api.operations.game.getAllGames.GetGameItem;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllGamesProcessor implements GetAllGamesOperation {
    private final GameRepository gameRepository;
    private final Logger logger;
    @Override
    public GetAllGamesResponse process(GetAllGamesRequest request) {
        try {
            List<GetGameItem> gameResponses = gameRepository.findAll()
                    .stream()
                    .map(this::mapGameToSingleItem)
                    .toList();
            logger.info("Retrieved all games successfully!");
            return GetAllGamesResponse.builder()
                    .games(gameResponses)
                    .build();
        }
        catch (Exception e){
            logger.error("Error retrieving all games!");
            throw new RuntimeException("Error retrieving all games!");
        }
    }

    private GetGameItem mapGameToSingleItem(Game game){
      return GetGameItem.builder()
              .name(game.getName())
              .story(game.getStory())
              .tags(game.getTags().toArray(String[]::new))
              .multimedia(game.getMultimedia().toArray(String[]::new))
              .description(game.getDescription())
              .build();
    }
}
