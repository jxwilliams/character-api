package com.jowuan.characterapi.repository;

import com.jowuan.characterapi.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findByNameContainingIgnoreCase(String name);

    List<Character> findByUniverse(String universe);

    List<Character> findBySpecies(String species);

}