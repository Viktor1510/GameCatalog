package com.example.gamecatalog.persistence.repositories;

import com.example.gamecatalog.persistence.entities.Game;
import com.example.gamecatalog.persistence.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Set;
import java.util.UUID;
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {
    Page<Publisher> findPublisherByGames(Set<Game> game, Pageable pageable);

}
