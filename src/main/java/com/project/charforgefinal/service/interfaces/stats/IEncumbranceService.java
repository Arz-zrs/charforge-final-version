package com.project.charforgefinal.service.interfaces.stats;

import com.project.charforgefinal.model.entity.character.PlayerCharacter;

public interface IEncumbranceService {
    double getCurrentWeight(PlayerCharacter character);
    double getMaxWeight(PlayerCharacter character, int totalStr);
}
