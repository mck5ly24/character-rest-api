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

    // Get all characters, filter by universe, or search by name
    public List<MarvelCharacter> getCharacters(String universe, String name) {
        if (name != null) {
            return repository.findByNameContainingIgnoreCase(name);
        }

        if (universe != null) {
            return repository.findByUniverseIgnoreCase(universe);
        }

        return repository.findAll();
    }

    // Get one character by ID
    public MarvelCharacter getCharacterById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Character not found"));
    }

    // Add a character
    public MarvelCharacter addCharacter(MarvelCharacter character) {
        character.setCharacterId(0);
        return repository.save(character);
    }

    // Update an existing character
    public MarvelCharacter updateCharacter(Long id, MarvelCharacter updated) {
        MarvelCharacter existing = getCharacterById(id);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setUniverse(updated.getUniverse());
        existing.setRole(updated.getRole());

        return repository.save(existing);
    }

    // Delete a character
    public void deleteCharacter(Long id) {
        MarvelCharacter existing = getCharacterById(id);
        repository.delete(existing);
    }
}
