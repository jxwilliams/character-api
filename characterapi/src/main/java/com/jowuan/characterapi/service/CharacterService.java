package com.jowuan.characterapi.service;

import com.jowuan.characterapi.entity.Character;
import com.jowuan.characterapi.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Optional<Character> getCharacterById(Long id) {
        return characterRepository.findById(id);
    }

    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Character updateCharacter(Long id, Character updatedCharacter) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            Character existingCharacter = optionalCharacter.get();
            existingCharacter.setName(updatedCharacter.getName());
            existingCharacter.setDescription(updatedCharacter.getDescription());
            existingCharacter.setUniverse(updatedCharacter.getUniverse());
            existingCharacter.setSpecies(updatedCharacter.getSpecies());

            return characterRepository.save(existingCharacter);
        }

        return null;
    }

    public boolean deleteCharacter(Long id) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);

        if (optionalCharacter.isPresent()) {
            characterRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<Character> searchByName(String name) {
        return characterRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Character> getByCategory(String category) {
        List<Character> byUniverse = characterRepository.findByUniverse(category);

        if (!byUniverse.isEmpty()) {
            return byUniverse;
        }

        return characterRepository.findBySpecies(category);
    }
}