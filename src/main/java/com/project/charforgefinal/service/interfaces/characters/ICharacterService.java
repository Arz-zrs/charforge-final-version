package com.project.charforgefinal.service.interfaces.characters;

import com.project.charforgefinal.model.entity.character.CharClass;
import com.project.charforgefinal.model.entity.character.Gender;
import com.project.charforgefinal.model.entity.character.PlayerCharacter;
import com.project.charforgefinal.model.entity.character.Race;

import java.util.List;

public interface ICharacterService {
    PlayerCharacter createCharacter(
            String name,
            Gender gender,
            Race race,
            CharClass charClass
    );
    void saveCharacterToDB(PlayerCharacter character);
    List<Race> getAllRaces();
    List<CharClass> getAllClasses();
    List<PlayerCharacter> findAllCharacters();
    boolean deleteCharacter(int characterId);
}
