package com.example.gamecatalog.persistence.repositories;

import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Multimedia;
import com.example.gamecatalog.persistence.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    Page<Game> findByTags(Set<Tag> tags, Pageable pageable);
    Page<Game> findByMultimedia(Set<Multimedia> multimedia, Pageable pageable);
}
