package com.example.gamecatalog.controllers;

import com.example.gamecatalog.api.operations.tag.createatag.CreateTagRequest;
import com.example.gamecatalog.api.operations.tag.createatag.CreateTagResponse;
import com.example.gamecatalog.api.operations.tag.deleteTag.DeleteTagRequest;
import com.example.gamecatalog.api.operations.tag.deleteTag.DeleteTagResponse;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetAllTagsRequest;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetAllTagsResponse;
import com.example.gamecatalog.api.operations.tag.getTag.GetTagRequest;
import com.example.gamecatalog.api.operations.tag.getTag.GetTagResponse;
import com.example.gamecatalog.api.operations.tag.updateTag.UpdateTagRequest;
import com.example.gamecatalog.api.operations.tag.updateTag.UpdateTagResponse;
import com.example.gamecatalog.core.processors.tag.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/tags")
@RequiredArgsConstructor
public class TagController {
    private final CreateTagProcessor createTagProcessor;
    private final GetTagProcessor getTagProcessor;
    private final GetAllTagsProcessor getAllTagsProcessor;
    private final UpdateTagProcessor updateTagProcessor;
    private final DeleteTagProcessor deleteTagProcessor;

    @PostMapping(path="/tag")
    @Operation(description = "Creates a tag that doesn't still exist.",
               summary = "Creates a tag.")
    public ResponseEntity<CreateTagResponse> createTag(@RequestParam String name){
        return new  ResponseEntity<>(this.createTagProcessor.process(CreateTagRequest.builder()
                        .name(name)
                .build()),HttpStatus.CREATED);
    }

    @GetMapping(path="/{tagId}")
    @Operation(description = "Gets tag by a given id",
               summary = "Gets tag")
    public ResponseEntity<GetTagResponse> getTag(@PathVariable @UUID String tagId)
    {
        return new ResponseEntity<>(this.getTagProcessor.process(GetTagRequest.builder()
                        .tagId(tagId)
                .build()),HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Gets all tags without any parameters.",
               summary = "Gets all tags")
    public ResponseEntity<GetAllTagsResponse> getAllTags(){
        return new ResponseEntity<>(this.getAllTagsProcessor.process(GetAllTagsRequest.builder()
                .build()),HttpStatus.OK);
    }

    @PatchMapping
    @Operation(description = "Updates a tag with given parameters",
               summary = "Updates tag's content.")
    public ResponseEntity<UpdateTagResponse> updateTag(@RequestParam @UUID String tagId, @RequestParam String name){
        return new ResponseEntity<>(this.updateTagProcessor.process(UpdateTagRequest.builder()
                        .tagId(tagId)
                        .name(name)
                .build()),HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(description = "Deletes a tag by given id.",
               summary = "Deletes tag.")
    public ResponseEntity<DeleteTagResponse> deleteTag(@RequestParam @UUID String tagId){
        return new ResponseEntity<>(this.deleteTagProcessor.process(DeleteTagRequest.builder()
                        .tagId(tagId)
                .build()),HttpStatus.OK);
    }

}
