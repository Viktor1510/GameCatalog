package com.example.gamecatalog.export;

import com.example.gamecatalog.api.operations.game.createGame.CreateGameResponse;
import com.example.gamecatalog.api.operations.game.deleteGame.DeleteGameResponse;
import com.example.gamecatalog.api.operations.game.getAllGames.GetAllGamesResponse;
import com.example.gamecatalog.api.operations.game.getGame.GetGameResponse;
import com.example.gamecatalog.api.operations.game.updateGame.UpdateGameResponse;
import com.example.gamecatalog.api.operations.publisher.createPublisher.CreatePublisherResponse;
import com.example.gamecatalog.api.operations.publisher.deletePublisher.DeletePublisherResponse;
import com.example.gamecatalog.api.operations.publisher.updatePublisher.UpdatePublisherResponse;
import com.example.gamecatalog.api.operations.tag.createatag.CreateTagResponse;
import com.example.gamecatalog.api.operations.tag.deleteTag.DeleteTagResponse;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetAllTagsResponse;
import com.example.gamecatalog.api.operations.tag.getTag.GetTagResponse;
import com.example.gamecatalog.api.operations.tag.updateTag.UpdateTagResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.time.LocalDate;

@Headers({"Content-Type: application/json"})
public interface GameCatalogExport {
 @RequestLine("POST /tags/tag")
 CreateTagResponse createTag(@Param String name);

 @RequestLine("GET /tags/{tagId}")
 GetTagResponse getTag(@Param("tagId") String tagId);

 @RequestLine("GET /tags")
 GetAllTagsResponse getAllTags();

 @RequestLine("PATCH /tags")
 UpdateTagResponse updateTag(@Param String tagId, @Param String name);

 @RequestLine("DELETE /tags")
 DeleteTagResponse deleteTag(@Param String tagId);

 @RequestLine("POST /games")
 CreateGameResponse createGame(@Param String name, @Param String story,
                               @Param String description, @Param String[] tags);

 @RequestLine("GET /games/{gameId}")
 GetGameResponse getGame(@Param("gameId")String gameId);

 @RequestLine("GET /games")
 GetAllGamesResponse getAllGames();

 @RequestLine("DELETE /games/{gameId}")
 DeleteGameResponse deleteGame(@Param("gameId")String gameId);

 @RequestLine("PATCH /games")
 UpdateGameResponse updateGame(@Param String gameId,@Param String name,
                               @Param String story, @Param String description,
                               @Param String[] tags);

 @RequestLine("POST /publishers")
 CreatePublisherResponse createPublisher(@Param String name, @Param String address,
                                         @Param LocalDate dateOfCreation, @Param String[] games);

 @RequestLine("GET /publishers/{publisherId}")
 GetGameResponse getPublisher(@Param("publisherId")String publisherId);

 @RequestLine("GET /publishers")
 GetAllGamesResponse getAllPublishers();

 @RequestLine("PATCH /publishers")
 UpdatePublisherResponse updatePublisher(@Param String publisherId,@Param String name, @Param String address,
                                         @Param LocalDate dateOfCreation, @Param String[] games);

 @RequestLine("DELETE /publishers/{publisherId}")
 DeletePublisherResponse deletePublisher(@Param("publisherId")String publisherId);
}
