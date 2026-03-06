package com.jowuan.characterapi.controller;

import com.jowuan.characterapi.entity.Character;
import com.jowuan.characterapi.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public List<Character> getAllCharacters() {
        return characterService.getAllCharacters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Optional<Character> character = characterService.getCharacterById(id);

        if (character.isPresent()) {
            return ResponseEntity.ok(character.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Character createCharacter(@RequestBody Character character) {
        return characterService.createCharacter(character);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character updatedCharacter) {
        Character character = characterService.updateCharacter(id, updatedCharacter);

        if (character != null) {
            return ResponseEntity.ok(character);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCharacter(@PathVariable Long id) {
        boolean deleted = characterService.deleteCharacter(id);

        if (deleted) {
            return ResponseEntity.ok("Character deleted successfully.");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Character> searchByName(@RequestParam String name) {
        return characterService.searchByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Character> getByCategory(@PathVariable String category) {
        return characterService.getByCategory(category);
    }
}