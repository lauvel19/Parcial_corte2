package org.arle.service;

import org.arle.entity.Concierto;
import org.arle.repository.ConciertoRepository;

import java.util.List;

public class ConciertoService {

    private final ConciertoRepository repository;

    public ConciertoService() {
        this.repository = new ConciertoRepository();
    }

    public void crearConcierto(Concierto concierto) {
        repository.crear(concierto);
    }

    public Concierto obtenerConcierto(Long id) {
        return repository.leer(id);
    }

    public List<Concierto> obtenerTodosLosConciertos() {
        return repository.leerTodos();
    }

    public void actualizarConcierto(Concierto concierto) {
        repository.actualizar(concierto);
    }

    public void eliminarConcierto(Long id) {
        repository.eliminar(id);
    }

    public void cerrar() {
        repository.cerrar();
    }
}
