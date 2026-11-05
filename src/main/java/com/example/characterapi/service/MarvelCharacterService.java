package com.example.characterapi.service;

import com.example.characterapi.entity.MarvelCharacter;
import com.example.characterapi.repository.MarvelCharacterRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MarvelCharacterService {

    private final MarvelCharacterRepository repository;

    public MarvelCharacterService(MarvelCharacterRepository repository) {
        this.repository = repository;
    }

    // Get all characters or apply name and universe filters.
    public List<MarvelCharacter> getCharacters(String universe, String name) {
        String universeFilter = universe == null ? "" : universe.trim();
        String nameFilter = name == null ? "" : name.trim();

        if (!universeFilter.isEmpty() && !nameFilter.isEmpty()) {
            return repository
                    .findByUniverseIgnoreCaseAndNameContainingIgnoreCase(
                            universeFilter, nameFilter);
        }

        if (!nameFilter.isEmpty()) {
            return repository.findByNameContainingIgnoreCase(nameFilter);
        }

        if (!universeFilter.isEmpty()) {
            return repository.findByUniverseIgnoreCase(universeFilter);
        }

        return repository.findAll();
    }

    // Get one character by ID.
    public MarvelCharacter getCharacterById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Character not found"));
    }

    // Add a character.
    public MarvelCharacter addCharacter(MarvelCharacter character) {
        character.setCharacterId(0);
        return repository.save(character);
    }

    // Update an existing character.
    public MarvelCharacter updateCharacter(Long id, MarvelCharacter updated) {
        MarvelCharacter existing = getCharacterById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setUniverse(updated.getUniverse());
        existing.setRole(updated.getRole());
        existing.setImageUrl(updated.getImageUrl());

        return repository.save(existing);
    }

    // Delete a character.
    public void deleteCharacter(Long id) {
        MarvelCharacter existing = getCharacterById(id);
        repository.delete(existing);
    }
}