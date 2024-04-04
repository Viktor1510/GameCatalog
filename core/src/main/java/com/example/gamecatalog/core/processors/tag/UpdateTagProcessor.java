package com.example.gamecatalog.core.processors.tag;

import com.example.gamecatalog.api.base.Processor;
import com.example.gamecatalog.api.operations.tag.updateTag.UpdateTagOperation;
import com.example.gamecatalog.api.operations.tag.updateTag.UpdateTagRequest;
import com.example.gamecatalog.api.operations.tag.updateTag.UpdateTagResponse;
import com.example.gamecatalog.core.exceptions.tag.TagNotFoundException;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateTagProcessor implements UpdateTagOperation {
private final TagRepository tagRepository;
private final Logger logger;
    @Override
    public UpdateTagResponse process(UpdateTagRequest request) {
        try {
            Optional<Tag> optionalTag = Optional.ofNullable(tagRepository.findById(UUID.fromString(request.getTagId()))
                    .orElseThrow(() -> new TagNotFoundException("Tag not found!")));
            Tag tag = optionalTag.get();
            tag.setName(request.getName());
            tagRepository.save(tag);

            logger.info("Tag updated successfully. TagId: {}, New name: {}", request.getTagId(), request.getName());

            return UpdateTagResponse.builder()
                    .name(tag.getName())
                    .build();
        } catch (Exception e) {
            logger.error("Error updating tag. TagId: {}, New name: {}", request.getTagId(), request.getName(), e);
            throw new RuntimeException("Error updating tag. TagId: " + request.getTagId(), e);
        }
    }
    }

