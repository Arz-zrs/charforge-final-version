package com.project.charforgefinal.service.interfaces.process;

import com.project.charforgefinal.model.entity.character.PlayerCharacter;
import com.project.charforgefinal.model.entity.inventory.InventoryItem;
import com.project.charforgefinal.model.entity.item.EquipmentSlot;

public interface IValidationService {
    boolean canEquip(PlayerCharacter character, InventoryItem inventoryItem, EquipmentSlot targetSlot);
}
