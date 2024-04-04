package com.example.gamecatalog.core.processors.tag;


import com.example.gamecatalog.api.operations.tag.createatag.CreateTagOperation;
import com.example.gamecatalog.api.operations.tag.createatag.CreateTagRequest;
import com.example.gamecatalog.api.operations.tag.createatag.CreateTagResponse;
import com.example.gamecatalog.persistence.entities.Tag;
import com.example.gamecatalog.persistence.repositories.TagRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTagProcessor implements CreateTagOperation {
    private final TagRepository tagRepository;
    private final Logger logger;

    @Override
    public CreateTagResponse process(CreateTagRequest request) {
        try {
            Tag tag = Tag.builder()
                    .name(request.getName())
                    .build();
            tagRepository.save(tag);
            logger.info("Tag created successfully. TagId: {}", tag.getTagId());
            return CreateTagResponse.builder()
                    .name(tag.getName())
                    .build();
        } catch (Exception e) {
            logger.error("Error creating tag.", e);
            throw new RuntimeException("Error creating tag.", e);
        }
    }
}
