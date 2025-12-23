package com.project.charforgefinal.service.interfaces.items;

import com.project.charforgefinal.model.entity.character.PlayerCharacter;
import com.project.charforgefinal.model.entity.inventory.InventoryItem;

import java.util.List;

public interface IInventoryService {
    List<InventoryItem> getInventory(PlayerCharacter character);
    void addItem(PlayerCharacter character, int itemId);
    void addTempItem(PlayerCharacter character, int itemId);
    void removeItem(PlayerCharacter character, InventoryItem item);
}
