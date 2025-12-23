package com.project.charforgefinal.dao.impl;

import com.project.charforgefinal.dao.base.BaseDao;
import com.project.charforgefinal.dao.interfaces.ItemDao;
import com.project.charforgefinal.db.ConnectionProvider;
import com.project.charforgefinal.model.entity.item.EquipmentSlot;
import com.project.charforgefinal.model.entity.item.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ItemDaoImpl extends BaseDao<Item> implements ItemDao {
    public ItemDaoImpl(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    @Override
    public List<Item> findAll() {
        return queryList("SELECT * FROM items", StatementBinder.empty());
    }

    @Override
    public Optional<Item> findById(int itemId) {
        String sql = "SELECT * FROM items WHERE id = ?";

        Item item = querySingle(
                sql,
                statement -> statement.setInt(1, itemId)
        );
        return Optional.ofNullable(item);
    }

    @Override
    protected Item mapRow(ResultSet result) throws SQLException {
        return new Item(
                result.getInt("id"),
                result.getString("name"),
                result.getDouble("weight"),
                EquipmentSlot.valueOf(result.getString("type").trim().toUpperCase()),
                result.getInt("stat_str"),
                result.getInt("stat_dex"),
                result.getInt("stat_int"),
                result.getInt("stat_hp"),
                result.getInt("stat_ap"),
                result.getInt("stat_atk"),
                result.getString("icon_path")
        );
    }
}
