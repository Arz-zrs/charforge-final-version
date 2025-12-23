package com.project.charforgefinal.dao.impl;

import com.project.charforgefinal.dao.base.BaseDao;
import com.project.charforgefinal.dao.interfaces.RaceDao;
import com.project.charforgefinal.db.ConnectionProvider;
import com.project.charforgefinal.model.entity.character.Race;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RaceDaoImpl extends BaseDao<Race> implements RaceDao {

    public RaceDaoImpl(ConnectionProvider connectionProvider) {
        super(connectionProvider);
    }

    @Override
    public List<Race> findAll() {
        return queryList("SELECT * FROM races", StatementBinder.empty());
    }

    @Override
    public Optional<Race> findById(int id) {
        Race race = querySingle(
                "SELECT * FROM races WHERE id = ?",
                statement -> statement.setInt(1, id)
        );
        return Optional.ofNullable(race);
    }

    @Override
    protected Race mapRow(ResultSet result) throws SQLException {
        return new Race(
                result.getInt("id"),
                result.getString("name"),
                result.getInt("base_hp"),
                result.getInt("base_str"),
                result.getInt("base_dex"),
                result.getInt("base_int"),
                result.getDouble("weight_capacity_modifier")
        );
    }
}
