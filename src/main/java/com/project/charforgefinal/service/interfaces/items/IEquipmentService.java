package com.project.charforgefinal.service.interfaces.items;

import com.project.charforgefinal.model.entity.character.PlayerCharacter;
import com.project.charforgefinal.model.entity.inventory.InventoryItem;
import com.project.charforgefinal.model.entity.item.EquipmentSlot;

import java.util.List;

public interface IEquipmentService {
    List<InventoryItem> loadInventory(PlayerCharacter character);
    boolean canEquip(PlayerCharacter character, int instanceId, EquipmentSlot targetSlot);
    void equip(PlayerCharacter character, int instanceId, EquipmentSlot slot);
    void unequip(PlayerCharacter character, int instanceId);
}
