package com.example.gamecatalog.controllers;

import com.example.gamecatalog.api.operations.game.deleteGame.DeleteGameResponse;
import com.example.gamecatalog.api.operations.multimedia.createMultimedia.CreateMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.createMultimedia.CreateMultimediaResponse;
import com.example.gamecatalog.api.operations.multimedia.deleteMultimedia.DeleteMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.deleteMultimedia.DeleteMultimediaResponse;
import com.example.gamecatalog.api.operations.multimedia.getAllMultimedia.GetAllMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.getAllMultimedia.GetAllMultimediaResponse;
import com.example.gamecatalog.api.operations.multimedia.getMultimedia.GetMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.getMultimedia.GetMultimediaResponse;
import com.example.gamecatalog.api.operations.multimedia.updateMultimedia.UpdateMultimediaRequest;
import com.example.gamecatalog.api.operations.multimedia.updateMultimedia.UpdateMultimediaResponse;
import com.example.gamecatalog.core.processors.multimedia.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/multimedia")
@RequiredArgsConstructor
public class MultimediaController {
    private final CreateMultimediaProcessor createMultimediaProcessor;
    private final GetAllMultimediaProcessor getAllMultimediaProcessor;
    private final GetMultimediaProcessor getMultimediaProcessor;
    private final UpdateMultimediaProcessor updateMultimediaProcessor;
    private final DeleteMultimediaProcessor deleteMultimediaProcessor;

    @PostMapping
    public ResponseEntity<CreateMultimediaResponse> createMultimedia(CreateMultimediaRequest createMultimediaRequest){
        return new ResponseEntity<>(this.createMultimediaProcessor.process(createMultimediaRequest), HttpStatus.CREATED);
    }

    @GetMapping(path = "/multimedia")
    public ResponseEntity<GetMultimediaResponse> getMultimedia(GetMultimediaRequest request){
        return new ResponseEntity<>(this.getMultimediaProcessor.process(request),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<GetAllMultimediaResponse> getAllMultimedia(GetAllMultimediaRequest request){
        return new ResponseEntity<>(this.getAllMultimediaProcessor.process(request), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UpdateMultimediaResponse> updateMultimedia(UpdateMultimediaRequest request){
        return new ResponseEntity<>(this.updateMultimediaProcessor.process(request),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DeleteMultimediaResponse> deleteMultimedia(DeleteMultimediaRequest request){
        return new ResponseEntity<>(this.deleteMultimediaProcessor.process(request),HttpStatus.OK);
    }
}
