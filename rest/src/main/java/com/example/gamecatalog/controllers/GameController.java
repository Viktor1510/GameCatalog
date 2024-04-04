package com.example.gamecatalog.controllers;

import com.example.gamecatalog.api.operations.game.createGame.CreateGameRequest;
import com.example.gamecatalog.api.operations.game.createGame.CreateGameResponse;
import com.example.gamecatalog.api.operations.game.deleteGame.DeleteGameRequest;
import com.example.gamecatalog.api.operations.game.deleteGame.DeleteGameResponse;
import com.example.gamecatalog.api.operations.game.getAllGames.GetAllGamesRequest;
import com.example.gamecatalog.api.operations.game.getAllGames.GetAllGamesResponse;
import com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia.GetAllGamesByMultimediaRequest;
import com.example.gamecatalog.api.operations.game.getAllGamesByMultimedia.GetAllGamesByMultimediaResponse;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesByTagRequest;
import com.example.gamecatalog.api.operations.game.getAllGamesByTag.GetAllGamesByTagResponse;
import com.example.gamecatalog.api.operations.game.getGame.GetGameRequest;
import com.example.gamecatalog.api.operations.game.getGame.GetGameResponse;
import com.example.gamecatalog.api.operations.game.updateGame.UpdateGameRequest;
import com.example.gamecatalog.api.operations.game.updateGame.UpdateGameResponse;
import com.example.gamecatalog.core.processors.game.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/games")
@RequiredArgsConstructor
public class GameController {

    private final CreateGameProcessor createGameProcessor;
    private final GetGameProcessor getGameProcessor;
    private final GetAllGamesProcessor getAllGamesProcessor;
    private final DeleteGameProcessor deleteGameProcessor;
    private final UpdateGameProcessor updateGameProcessor;
    private final GetAllGamesByTagProcessor getAllGamesByTagProcessor;
    private final GetAllGamesByMultimediaProcessor getAllGamesByMultimediaProcessor;

    @PostMapping(path = "/game")
    @Operation(description = "Creates a game with given parameters.",
               summary = "Creates a game.")
    public ResponseEntity<CreateGameResponse> createGame(@RequestParam String name,
                                                         @RequestParam String story,
                                                         @RequestParam String description,
                                                         @RequestParam String[] tags,
                                                         @RequestParam String[] multimedia)
    {
        return new ResponseEntity<>(this.createGameProcessor.process(CreateGameRequest.builder()
                        .name(name)
                        .description(description)
                        .story(story)
                        .tags(tags)
                        .multimedia(multimedia)
                .build()), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{gameId}")
    @Operation(description = "Gets a game by given id.",
              summary = "Gets a game.")
    public ResponseEntity<GetGameResponse> getGame(@PathVariable @UUID String gameId){
        return new ResponseEntity<>(this.getGameProcessor.process(GetGameRequest.builder()
                        .gameId(gameId)
                        .build()),HttpStatus.OK );
    }

   @GetMapping(path = "/games")
   @Operation(description = "Gets all games without parameters.",
              summary = "Gets all games.")
    public ResponseEntity<GetAllGamesResponse> getAllGames(){
        return new ResponseEntity<>(this.getAllGamesProcessor.process(GetAllGamesRequest.builder()
                .build()),HttpStatus.OK);
   }

   @DeleteMapping(path="/{gameId}")
   @Operation(description = "Deletes a game with given id.",
              summary = "Deletes a game.")
    public ResponseEntity<DeleteGameResponse> deleteGame(@PathVariable @UUID String gameId){
       return new ResponseEntity<>(this.deleteGameProcessor.process(DeleteGameRequest.builder()
                       .gameId(gameId)
               .build()),HttpStatus.OK);
   }

   @PatchMapping(path = "/game")
   @Operation(description = "Updates a game with given parameters.",
              summary = "Updates a game.")
    public ResponseEntity<UpdateGameResponse> updateGame(@RequestParam @UUID String gameId, @RequestParam String name,
                                                         @RequestParam String story, @RequestParam String description,
                                                         @RequestParam String[] tags,
                                                         @RequestParam String[] multimedia){
        return new ResponseEntity<>(this.updateGameProcessor.process(UpdateGameRequest.builder()
                        .gameId(gameId)
                        .description(description)
                        .name(name)
                        .story(story)
                        .tags(tags)
                        .multimedia(multimedia)
                .build()),HttpStatus.OK);
   }

   @GetMapping(path = "/games/tag")
    public ResponseEntity<GetAllGamesByTagResponse> getAllGamesByTag(@RequestParam @UUID String tagId,
                                                                     @RequestParam Integer startPage,
                                                                     @RequestParam Integer size){
        return new ResponseEntity<>(this.getAllGamesByTagProcessor.process(GetAllGamesByTagRequest.builder()
                        .tagId(tagId)
                        .size(size)
                        .startPage(startPage)
                        .build()),HttpStatus.OK);
   }

   @GetMapping(path="/games/multimedia")
    public ResponseEntity<GetAllGamesByMultimediaResponse> getAllGamesByMultimedia(@RequestParam @UUID String multimediaId,
                                                                                   @RequestParam Integer startPage,
                                                                                   @RequestParam Integer size){
        return new ResponseEntity<>(this.getAllGamesByMultimediaProcessor.process(GetAllGamesByMultimediaRequest
                .builder()
                .multimediaId(multimediaId)
                .size(size)
                .startPage(startPage)
                .build()),
                 HttpStatus.OK);
   }
}
