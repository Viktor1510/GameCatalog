package com.example.gamecatalog.core.processors.game;

import com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia.GetAllGamesByMultimediaOperation;
import com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia.GetAllGamesByMultimediaRequest;
import com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia.GetAllGamesByMultimediaResponse;
import com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia.GetGameByMultimedia;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesByTagResponse;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesSingleItem;
import com.example.gamecatalog.core.exceptions.multimedia.MultimediaNotFoundException;
import com.example.gamecatalog.core.exceptions.tag.TagNotFoundException;
import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.GameRepository;
import com.example.gamecatalog.persistence.repositories.MultimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
public class GetAllGamesByMultimediaProcessor implements GetAllGamesByMultimediaOperation {
     private final GameRepository gameRepository;
     private final MultimediaRepository multimediaRepository;
     private final Logger logger;

    @Override
    public GetAllGamesByMultimediaResponse process(GetAllGamesByMultimediaRequest request) {
        try {
            Multimedia multimedia=multimediaRepository.findById(UUID.fromString(request.getMultimediaId()))
                    .orElseThrow(()->new MultimediaNotFoundException("Multimedia not found!"));
            Pageable pageable = (Pageable) PageRequest.of(request.getStartPage(), request.getSize(),
                    Sort.by("name").ascending());
            Page<Game> gamesPage = gameRepository.findByMultimedia((Set<Multimedia>) multimedia,
                    (org.springframework.data.domain.Pageable) pageable);

            List<GetGameByMultimedia> gameResponses = gamesPage.getContent().stream()
                    .map(this::mapGameToSingleItem)
                    .toList();
            logger.info("Retrieved games by multimedia successfully!");

            return GetAllGamesByMultimediaResponse.builder()
                    .responses(gameResponses)
                    .build();
        } catch (Exception e) {
            logger.error("Error retrieving games by multimedia!");
            throw new RuntimeException("Error retrieving games by multimedia!");
        }
    }

    private GetGameByMultimedia mapGameToSingleItem(Game game) {
        return GetGameByMultimedia.builder()
                .name(game.getName())
                .story(game.getStory())
                .tags(game.getTags().toArray(String[]::new))
                .multimedia(game.getMultimedia().toArray(String[]::new))
                .description(game.getDescription())
                .build();
    }
}
