package com.project.charforgefinal.dao.interfaces;

import com.project.charforgefinal.model.entity.character.Race;

import java.util.List;
import java.util.Optional;

public interface RaceDao {
    List<Race> findAll();
    Optional<Race> findById(int id);
}
