package com.project.charforgefinal.service.interfaces.characters;

import com.project.charforgefinal.model.entity.character.PlayerCharacter;
import com.project.charforgefinal.model.entity.inventory.InventoryItem;

import java.util.List;

public interface ICharacterInventoryService {
    List<InventoryItem> getInventory(PlayerCharacter character);
    void addItem(PlayerCharacter character, int itemId);
    void removeItem(PlayerCharacter character, int inventoryIndex);
}
