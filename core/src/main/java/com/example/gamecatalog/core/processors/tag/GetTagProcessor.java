package com.example.gamecatalog.core.processors.tag;

import com.example.gamecatalog.api.operations.tag.getTag.GetTagOperation;
import com.example.gamecatalog.api.operations.tag.getTag.GetTagRequest;
import com.example.gamecatalog.api.operations.tag.getTag.GetTagResponse;
import com.example.gamecatalog.core.exceptions.tag.TagNotFoundException;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetTagProcessor implements GetTagOperation {
    private final TagRepository tagRepository;
    private final Logger logger;
    @Override
    public GetTagResponse process(GetTagRequest request) {
        try {
            Optional<Tag> tag = Optional.ofNullable(tagRepository.findById(UUID.fromString(request.getTagId()))
                    .orElseThrow(() -> new TagNotFoundException("Tag not found!")));

            logger.info("Tag retrieved successfully. TagId: {}", request.getTagId());

            return GetTagResponse.builder()
                    .name(tag.get().getName())
                    .build();
        } catch (Exception e) {
            logger.error("Error retrieving tag. TagId: {}", request.getTagId(), e);
            throw new RuntimeException("Error retrieving tag. TagId: " + request.getTagId(), e);
        }
    }
}
