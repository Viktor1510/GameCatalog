package com.example.gamecatalog.core.processors.tag;

import com.example.gamecatalog.api.operations.tag.getAllTags.GetAllTagsOperation;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetAllTagsRequest;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetAllTagsResponse;
import com.example.gamecatalog.api.operations.tag.getAllTags.GetTagItem;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTagsProcessor implements GetAllTagsOperation {
    private final TagRepository tagRepository;
    private final Logger logger;
    @Override
    public GetAllTagsResponse process(GetAllTagsRequest request) {
        try {

            List<GetTagItem> tagResponses = tagRepository.findAll()
                    .stream()
                    .map(this::mapTagToSingleItem)
                    .toList();

            logger.info("Retrieved all tags successfully.");

            return GetAllTagsResponse.builder()
                    .tags(tagResponses)
                    .build();
        } catch (Exception e) {
            logger.error("Error retrieving all tags.", e);
            throw new RuntimeException("Error retrieving all tags.", e);
        }
    }

    private GetTagItem mapTagToSingleItem(Tag tag) {
        return GetTagItem.builder()
                .tagId(tag.getTagId().toString())
                .name(tag.getName())
                .build();
    }
}

