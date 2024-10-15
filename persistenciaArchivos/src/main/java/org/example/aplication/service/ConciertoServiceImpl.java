package org.example.aplication.service;

import org.example.domain.Concierto;
import org.example.interfaces.ConciertoRepository;

import java.util.List;

public class ConciertoServiceImpl implements ConciertoService {
    private final ConciertoRepository conciertoRepository;

    public ConciertoServiceImpl(ConciertoRepository conciertoRepository) {
        this.conciertoRepository = conciertoRepository;
    }

    @Override
    public List<Concierto> findAll() {
        return conciertoRepository.findAll();
    }

    @Override
    public Concierto findById(int id) {
        return conciertoRepository.findById(id);
    }

    @Override
    public void save(Concierto concierto) {
        validarConcierto(concierto);
        conciertoRepository.save(concierto);
    }

    @Override
    public void update(Concierto concierto) {
        validarConcierto(concierto);
        conciertoRepository.update(concierto);
    }

    @Override
    public void delete(int id) {
        conciertoRepository.delete(id);
    }

    private void validarConcierto(Concierto concierto) {
        if (concierto.getNombre() == null || concierto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del concierto no puede estar vacío");
        }
        if (concierto.getFecha() == null) {
            throw new IllegalArgumentException("La fecha del concierto no puede ser nula");
        }
    }
}
