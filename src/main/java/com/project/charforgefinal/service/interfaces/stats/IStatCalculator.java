package com.project.charforgefinal.service.interfaces.stats;

import com.project.charforgefinal.model.dto.StatSnapshot;
import com.project.charforgefinal.model.entity.character.PlayerCharacter;

public interface IStatCalculator {
    StatSnapshot calculate(PlayerCharacter character);
}
