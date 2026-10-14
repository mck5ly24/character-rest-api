package com.example.characterapi.controller;

import com.example.characterapi.entity.MarvelCharacter;
import com.example.characterapi.service.MarvelCharacterService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class MarvelCharacterController {

    private final MarvelCharacterService service;

    public MarvelCharacterController(MarvelCharacterService service) {
        this.service = service;
    }

    // GET all characters, filter by universe, or search by name
    @GetMapping
    public List<MarvelCharacter> getCharacters(
            @RequestParam(required = false) String universe,
            @RequestParam(required = false) String name) {

        return service.getCharacters(universe, name);
    }

    // GET one character by ID
    @GetMapping("/{id}")
    public MarvelCharacter getCharacterById(@PathVariable Long id) {
        return service.getCharacterById(id);
    }

    // POST a new character
    @PostMapping
    public ResponseEntity<MarvelCharacter> addCharacter(
            @Valid @RequestBody MarvelCharacter character) {

        MarvelCharacter saved = service.addCharacter(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT update an existing character
    @PutMapping("/{id}")
    public MarvelCharacter updateCharacter(
            @PathVariable Long id,
            @Valid @RequestBody MarvelCharacter character) {

        return service.updateCharacter(id, character);
    }

    // DELETE a character
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCharacter(@PathVariable Long id) {
        service.deleteCharacter(id);
    }
}

