package com.example.gamecatalog.controllers;

import com.example.gamecatalog.api.operations.publisher.createPublisher.CreatePublisherRequest;
import com.example.gamecatalog.api.operations.publisher.createPublisher.CreatePublisherResponse;
import com.example.gamecatalog.api.operations.publisher.deletePublisher.DeletePublisherRequest;
import com.example.gamecatalog.api.operations.publisher.deletePublisher.DeletePublisherResponse;
import com.example.gamecatalog.api.operations.publisher.getAllPublishers.GetAllPublishersRequest;
import com.example.gamecatalog.api.operations.publisher.getAllPublishers.GetAllPublishersResponse;
import com.example.gamecatalog.api.operations.publisher.getPublisher.GetPublisherRequest;
import com.example.gamecatalog.api.operations.publisher.getPublisher.GetPublisherResponse;
import com.example.gamecatalog.api.operations.publisher.updatePublisher.UpdatePublisherRequest;
import com.example.gamecatalog.api.operations.publisher.updatePublisher.UpdatePublisherResponse;
import com.example.gamecatalog.core.processors.publisher.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final CreatePublisherProcessor createPublisherProcessor;
    private final GetPublisherProcessor getPublisherProcessor;
    private final GetAllPublishersProcessor getAllPublishersProcessor;
    private final UpdatePublisherProcessor updatePublisherProcessor;
    private final DeletePublisherProcessor deletePublisherProcessor;

    @PostMapping
    @Operation(description = "Creates a publisher by given parameters.",
              summary = "Creates a publisher.")
    public ResponseEntity<CreatePublisherResponse> createPublisher(@RequestParam String name, @RequestParam String address,
                                                                   @RequestParam LocalDate dateOfCreation,
                                                                   @RequestParam String[] games){
        return new ResponseEntity<>(this.createPublisherProcessor.process(CreatePublisherRequest.builder()
                        .name(name)
                        .address(address)
                        .dateOfCreation(dateOfCreation)
                        .games(games)
                .build()), HttpStatus.CREATED);
    }

    @GetMapping(path="/{publisherId}")
    @Operation(description = "Gets a publisher by given id.",
               summary = "Gets a publisher.")
    public ResponseEntity<GetPublisherResponse> getPublisher(@PathVariable @UUID String publisherId){
        return new ResponseEntity<>(this.getPublisherProcessor.process(GetPublisherRequest.builder()
                        .publisherId(publisherId)
                .build()),HttpStatus.OK);
    }

    @GetMapping
    @Operation(description = "Gets all publishers without any parameters.",
               summary = "Gets all publishers.")
    public ResponseEntity<GetAllPublishersResponse> getAllPublishers(){
        return new ResponseEntity<>(this.getAllPublishersProcessor.process(GetAllPublishersRequest.builder()
                .build()),HttpStatus.OK);
    }

    @PatchMapping
    @Operation(description = "Updates a publishers by given parameters.",
               summary = "Updates a publisher.")
    public ResponseEntity<UpdatePublisherResponse> updatePublisher(@RequestParam @UUID String publisherId,@RequestParam String name,
                                                                   @RequestParam String address, @RequestParam LocalDate dateOfCreation,
                                                                   @RequestParam String[] games){
        return new ResponseEntity<>(this.updatePublisherProcessor.process(UpdatePublisherRequest.builder()
                        .publisherId(publisherId)
                        .address(address)
                        .dateOfCreation(dateOfCreation)
                        .name(name)
                        .games(games)
                .build()),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{publisherId}")
    @Operation(description = "Deletes a publisher by given id.",
               summary = "Deletes a publisher.")
    public ResponseEntity<DeletePublisherResponse> deletePublisher(@PathVariable @UUID String publisherId){
        return new ResponseEntity<>(this.deletePublisherProcessor.process(DeletePublisherRequest.builder()
                        .publisherId(publisherId)
                .build()),HttpStatus.OK);
    }
}
