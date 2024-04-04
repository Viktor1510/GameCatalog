package com.example.gamecatalog.core.processors.game;

import com.example.gamecatalog.api.operations.game.createGame.CreateGameOperation;
import com.example.gamecatalog.api.operations.game.createGame.CreateGameRequest;
import com.example.gamecatalog.api.operations.game.createGame.CreateGameResponse;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import com.example.gamecatalog.persistence.repositories.MultimediaRepository;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateGameProcessor implements CreateGameOperation {
    private final GameRepository gameRepository;
    private final TagRepository tagRepository;
    private final MultimediaRepository multimediaRepository;
    private final Logger logger;
    @Override
    public CreateGameResponse process(CreateGameRequest request) {
        try {
            Game game = Game.builder()
                    .name(request.getName())
                    .story(request.getStory())
                    .description(request.getDescription())
                    .tags(getAllTagIds(request.getTags()))
                    .multimedia(getAllMultimediaIds(request.getMultimedia()))
                    .build();
            gameRepository.save(game);


            logger.info("Game '{}' created successfully with ID: {}", game.getName(), game.getGameId());

            return CreateGameResponse.builder()
                    .name(game.getName())
                    .story(game.getStory())
                    .description(game.getDescription())
                    .tags(game.getTags().stream().map(Tag::getTagId).map(UUID::toString).toArray(String[]::new))
                    .multimedia(game.getMultimedia().stream().map(Multimedia::getMultimediaId).map(UUID::toString).toArray(String[]::new))
                    .build();
        }
        catch (RuntimeException e)
        {
            logger.error("An error occurred while creating a game: {}", e.getMessage(), e);
            throw new RuntimeException();
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
