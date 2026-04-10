package com.jowuan.characterapi.controller;

import com.jowuan.characterapi.entity.Character;
import com.jowuan.characterapi.service.CharacterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
@RequestMapping("/characters")
public class CharacterViewController {

    // This service is basically doing the database work for us,
    // so the controller can stay focused on pages and routes.
    private final CharacterService characterService;

    public CharacterViewController(CharacterService characterService) {
        this.characterService = characterService;
    }

    // Main page for the MVC side of the app.
    // This grabs every character and sends the list to character-list.ftlh.
    @GetMapping
    public String getAllCharacters(Model model) {
        model.addAttribute("characters", characterService.getAllCharacters());
        return "character-list";
    }

    // Details page for one character.
    // If the id does not exist, we throw a 404 so Spring shows it as "not found."
    @GetMapping("/{id}")
    public String getCharacterById(@PathVariable Long id, Model model) {
        Character character = characterService.getCharacterById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Character not found"));

        model.addAttribute("character", character);
        return "character-details";
    }

    // Just returns the About page template.
    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }

    // This loads the create form with a blank Character object.
    // The extra model attributes help the shared form know it is in "create mode."
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("character", new Character());
        model.addAttribute("formAction", "/characters/create");
        model.addAttribute("formTitle", "Add a Hero");
        model.addAttribute("formSubtitle", "Create a new character and save it to the gallery.");
        model.addAttribute("submitLabel", "Submit");
        return "character-create";
    }

    // This handles the submitted create form.
    // After saving, we redirect to the new character's details page.
    @PostMapping("/create")
    public String createCharacter(@ModelAttribute Character character) {
        Character savedCharacter = characterService.createCharacter(character);
        return "redirect:/characters/" + savedCharacter.getCharacterId();
    }

    // This loads the same form template, but this time with existing data filled in.
    // So instead of making a second template, we just reuse the create form for updates too.
    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        Character character = characterService.getCharacterById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Character not found"));

        model.addAttribute("character", character);
        model.addAttribute("formAction", "/characters/update/" + id);
        model.addAttribute("formTitle", "Update Hero");
        model.addAttribute("formSubtitle", "Edit this character and save the changes.");
        model.addAttribute("submitLabel", "Update");
        return "character-create";
    }

    // This processes the update form submission.
    // The service returns null if the character was not found,
    // so we turn that into a proper 404 instead of silently failing.
    @PostMapping("/update/{id}")
    public String updateCharacter(@PathVariable Long id, @ModelAttribute Character character) {
        Character updatedCharacter = characterService.updateCharacter(id, character);

        if (updatedCharacter == null) {
            throw new ResponseStatusException(NOT_FOUND, "Character not found");
        }

        return "redirect:/characters/" + updatedCharacter.getCharacterId();
    }

    // Super simple delete route for the MVC flow.
    // If delete works, we go back to the list page.
    @GetMapping("/delete/{id}")
    public String deleteCharacter(@PathVariable Long id) {
        boolean deleted = characterService.deleteCharacter(id);

        if (!deleted) {
            throw new ResponseStatusException(NOT_FOUND, "Character not found");
        }

        return "redirect:/characters";
    }
}
