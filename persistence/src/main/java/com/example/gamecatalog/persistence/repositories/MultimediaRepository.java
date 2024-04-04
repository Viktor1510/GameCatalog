package com.example.gamecatalog.persistence.repositories;

import com.example.gamecatalog.persistence.entities.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, UUID> {
}
