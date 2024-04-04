package com.example.gamecatalog.core.processors.game;

import com.example.gamecatalog.api.operations.game.getGame.GetGameOperation;
import com.example.gamecatalog.api.operations.game.getGame.GetGameRequest;
import com.example.gamecatalog.api.operations.game.getGame.GetGameResponse;
import com.example.gamecatalog.core.exceptions.game.GameNotFoundException;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetGameProcessor implements GetGameOperation {
    private final GameRepository gameRepository;
    private final Logger logger;
    @Override
    public GetGameResponse process(GetGameRequest request) {
        try
        {
            Optional<Game> gameOptional = Optional.ofNullable(this.gameRepository.findById(UUID.fromString(request.getGameId()))
                    .orElseThrow(() -> new GameNotFoundException("Game not found!")));
            Game game=gameOptional.get();
            logger.info("Game with id {} was found!",game.getGameId());
            return GetGameResponse.builder()
                    .tags(game.getTags().toArray(String[]::new))
                    .story(game.getStory())
                    .description(game.getDescription())
                    .name(game.getName())
                    .multimedia(game.getMultimedia().toArray(String[]::new))
                    .build();
        }
        catch (Exception e)
        {
            logger.error("Error finding game!");
            throw new RuntimeException("Error finding game!");
        }
    }


}
