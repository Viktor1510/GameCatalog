package com.example.gamecatalog.core.processors.game;

import com.example.gamecatalog.api.operations.game.deleteGame.DeleteGameOperation;
import com.example.gamecatalog.api.operations.game.deleteGame.DeleteGameRequest;
import com.example.gamecatalog.api.operations.game.deleteGame.DeleteGameResponse;
import com.example.gamecatalog.core.exceptions.game.GameNotFoundException;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteGameProcessor implements DeleteGameOperation {
    private final GameRepository gameRepository;
    private final Logger logger;

    @Override
    public DeleteGameResponse process(DeleteGameRequest request) {
        try {
            Optional<Game> gameOptional = Optional.ofNullable(this.gameRepository.findById(UUID.fromString(request.getGameId()))
                    .orElseThrow(() -> new GameNotFoundException("Game not found!")));
            Game game=gameOptional.get();
            this.gameRepository.delete(game);
            logger.info("Game with id {} deleted successfully!",game.getGameId());
            return DeleteGameResponse.builder().build();
        }
        catch (Exception e)
        {
            logger.error("Error deleting game!");
            throw new RuntimeException("Error deleting game!");

        }

    }

}
