package com.example.gamecatalog.core.processors.tag;

import com.example.gamecatalog.api.base.Processor;
import com.example.gamecatalog.api.operations.tag.deleteTag.DeleteTagOperation;
import com.example.gamecatalog.api.operations.tag.deleteTag.DeleteTagRequest;
import com.example.gamecatalog.api.operations.tag.deleteTag.DeleteTagResponse;
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
public class DeleteTagProcessor implements DeleteTagOperation {
    private final TagRepository tagRepository;
    private final Logger logger;


    @Override
    public DeleteTagResponse process(DeleteTagRequest request) {
        try {
            Optional<Tag> tagOptional = Optional.ofNullable(tagRepository.findById(UUID.fromString(request.getTagId()))
                    .orElseThrow(() -> new TagNotFoundException("Tag not found!")));
            Tag tag = tagOptional.get();
            tagRepository.delete(tag);
            logger.info("Tag deleted successfully. TagId: {}", tag.getTagId());
            return DeleteTagResponse.builder()
                    .name(tag.getName())
                    .build();
        } catch (TagNotFoundException e) {
            logger.error("Error deleting tag. Tag not found.", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting tag.", e);
            throw new RuntimeException("Error deleting tag.", e);
        }
    }
}
