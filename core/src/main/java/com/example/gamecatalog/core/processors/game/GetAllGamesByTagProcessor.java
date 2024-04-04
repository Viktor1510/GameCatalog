package com.example.gamecatalog.core.processors.game;

import com.example.gamecatalog.api.operations.game.getAllGames.GetAllGamesResponse;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesByTagOperation;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesByTagRequest;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesByTagResponse;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesSingleItem;
import com.example.gamecatalog.core.exceptions.tag.TagNotFoundException;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAllGamesByTagProcessor implements GetAllGamesByTagOperation {
    private final TagRepository tagRepository;
    private final GameRepository gameRepository;
    private final Logger logger;
    @Override
    public GetAllGamesByTagResponse process(GetAllGamesByTagRequest request) {
        try {
            Tag tag=tagRepository.findById(UUID.fromString(request.getTagId()))
                    .orElseThrow(()->new TagNotFoundException("Tag not found!"));
            Pageable pageable = (Pageable) PageRequest.of(request.getStartPage(), request.getSize(),
                    Sort.by("name").ascending());
            Page<Game> gamesPage = gameRepository.findByTags((Set<Tag>) tag,
                    (org.springframework.data.domain.Pageable) pageable);

            List<GetAllGamesSingleItem> gameResponses = gamesPage.getContent().stream()
                    .map(this::mapGameToSingleItem)
                    .toList();
            logger.info("Retrieved games by tag successfully!");

            return GetAllGamesByTagResponse.builder()
                    .responses(gameResponses)
                    .build();
        } catch (Exception e) {
            logger.error("Error retrieving games by tag!");
            throw new RuntimeException("Error retrieving games by tag!");
        }
    }

    private GetAllGamesSingleItem mapGameToSingleItem(Game game) {
        return GetAllGamesSingleItem.builder()
                .name(game.getName())
                .story(game.getStory())
                .tags(game.getTags().toArray(String[]::new))
                .multimedia(game.getMultimedia().toArray(String[]::new))
                .description(game.getDescription())
                .build();
    }
}
