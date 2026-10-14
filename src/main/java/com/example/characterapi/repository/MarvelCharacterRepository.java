package com.example.characterapi.repository;

import com.example.characterapi.entity.MarvelCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MarvelCharacterRepository
        extends JpaRepository<MarvelCharacter, Long> {

    List<MarvelCharacter> findByUniverseIgnoreCase(String universe);

    List<MarvelCharacter> findByNameContainingIgnoreCase(String name);
}
