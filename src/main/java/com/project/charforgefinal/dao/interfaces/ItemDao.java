package com.project.charforgefinal.dao.interfaces;

import com.project.charforgefinal.model.entity.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {
    List<Item> findAll();
    Optional<Item> findById(int itemId);
}
