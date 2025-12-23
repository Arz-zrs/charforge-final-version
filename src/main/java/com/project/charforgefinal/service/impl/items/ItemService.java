package com.project.charforgefinal.service.impl.items;

import com.project.charforgefinal.dao.interfaces.ItemDao;
import com.project.charforgefinal.model.entity.item.Item;
import com.project.charforgefinal.service.interfaces.items.IItemService;

import java.util.List;

public class ItemService implements IItemService {
    private final ItemDao itemDao;

    public ItemService(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public List<Item> getAllItems() {
        return itemDao.findAll();
    }
}
