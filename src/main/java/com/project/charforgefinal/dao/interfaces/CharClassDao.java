package com.project.charforgefinal.dao.interfaces;

import com.project.charforgefinal.model.entity.character.CharClass;

import java.util.List;
import java.util.Optional;

public interface CharClassDao {
    List<CharClass> findAll();
    Optional<CharClass> findById(int classId);
}
