package com.example.gamecatalog.core.processors.game;

import com.example.gamecatalog.api.operations.game.updateGame.UpdateGameOperation;
import com.example.gamecatalog.api.operations.game.updateGame.UpdateGameRequest;
import com.example.gamecatalog.api.operations.game.updateGame.UpdateGameResponse;
import com.example.gamecatalog.core.exceptions.game.GameNotFoundException;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import com.example.gamecatalog.persistence.repositories.MultimediaRepository;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateGameProcessor implements UpdateGameOperation {
    private final GameRepository gameRepository;
    private final TagRepository tagRepository;
    private final MultimediaRepository multimediaRepository;
    private final Logger logger;
    @Override
    public UpdateGameResponse process(UpdateGameRequest request) {
        try {
            Optional<Game> gameOptional = Optional.ofNullable(this.gameRepository.findById(UUID.fromString(request.getGameId()))
                    .orElseThrow(() -> new GameNotFoundException("Game not found!")));
            Game game = Game.builder()
                    .gameId(gameOptional.get().getGameId())
                    .name(request.getName())
                    .story(request.getStory())
                    .description(request.getDescription())
                    .tags(getAllTagIds(request.getTags()))
                    .multimedia(getAllMultimediaIds(request.getMultimedia()))
                    .build();
            this.gameRepository.save(game);
            logger.info("Game with id {} successfully!", game.getGameId());
            return UpdateGameResponse.builder()
                    .name(game.getName())
                    .tags(game.getTags().toArray(String[]::new))
                    .multimedia(game.getMultimedia().toArray(String[]::new))
                    .description(game.getDescription())
                    .build();
        }
        catch (Exception e)
        {
            logger.error("Error updating game!");
            throw new RuntimeException("Error updating game!");
        }
    }

    private Set<Tag> getAllTagIds(String[] tagIds) {
        return Arrays.stream(tagIds)
                .map(id -> tagRepository.findById(UUID.fromString(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
    private Set<Multimedia> getAllMultimediaIds(String[] multimediaIds)
    {
        return Arrays.stream(multimediaIds)
                .map(id -> multimediaRepository.findById(UUID.fromString(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}
