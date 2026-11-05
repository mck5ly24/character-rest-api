package com.example.characterapi;

import com.example.characterapi.entity.MarvelCharacter;
import com.example.characterapi.service.MarvelCharacterService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CharacterUiController {

    private final MarvelCharacterService service;

    public CharacterUiController(MarvelCharacterService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String gallery(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String universe,
            Model model) {

        model.addAttribute("characters", service.getCharacters(universe, name));
        model.addAttribute("name", name == null ? "" : name);
        model.addAttribute("universe", universe == null ? "" : universe);

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/characters/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("character", service.getCharacterById(id));
        return "details";
    }

    @GetMapping("/characters/new")
    public String showAddForm(Model model) {
        prepareForm(model, new MarvelCharacter(), false);
        return "character-form";
    }

    @PostMapping("/characters")
    public String addCharacter(
            @Valid @ModelAttribute("character") MarvelCharacter character,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            prepareForm(model, character, false);
            model.addAttribute("errors", result.getAllErrors());
            return "character-form";
        }

        MarvelCharacter saved = service.addCharacter(character);

        redirectAttributes.addFlashAttribute(
                "successMessage", saved.getName() + " was added.");

        return "redirect:/";
    }

    @GetMapping("/characters/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        prepareForm(model, service.getCharacterById(id), true);
        return "character-form";
    }

    @PostMapping("/characters/{id}")
    public String updateCharacter(
            @PathVariable Long id,
            @Valid @ModelAttribute("character") MarvelCharacter character,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Check the ID even if the submitted form contains invalid values.
        service.getCharacterById(id);
        character.setCharacterId(id);

        if (result.hasErrors()) {
            prepareForm(model, character, true);
            model.addAttribute("errors", result.getAllErrors());
            return "character-form";
        }

        MarvelCharacter saved = service.updateCharacter(id, character);

        redirectAttributes.addFlashAttribute(
                "successMessage", saved.getName() + " was updated.");

        return "redirect:/characters/" + saved.getCharacterId();
    }

    @PostMapping("/characters/{id}/delete")
    public String deleteCharacter(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        MarvelCharacter character = service.getCharacterById(id);
        service.deleteCharacter(id);

        redirectAttributes.addFlashAttribute(
                "successMessage", character.getName() + " was removed.");

        return "redirect:/";
    }

    private void prepareForm(
            Model model,
            MarvelCharacter character,
            boolean editing) {

        model.addAttribute("character", character);
        model.addAttribute("formTitle",
                editing ? "Edit Character" : "Add a Character");
        model.addAttribute("formAction",
                editing
                        ? "/characters/" + character.getCharacterId()
                        : "/characters");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String handleNotFound(
            ResponseStatusException exception,
            Model model,
            HttpServletResponse response) {

        if (exception.getStatusCode().value() != HttpStatus.NOT_FOUND.value()) {
            throw exception;
        }

        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("message", "That character could not be found.");

        return "error";
    }
}