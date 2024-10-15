package org.example.interfaces;

import org.example.domain.Concierto;

import java.util.List;

public interface ConciertoRepository {
    List<Concierto> findAll();
    Concierto findById(int id);
    void save(Concierto concierto);
    void update(Concierto concierto);
    void delete(int id);
}