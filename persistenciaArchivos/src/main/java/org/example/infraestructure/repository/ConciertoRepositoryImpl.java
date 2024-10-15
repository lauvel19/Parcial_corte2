package org.example.infraestructure.repository;

import org.example.domain.Concierto;
import org.example.interfaces.ConciertoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConciertoRepositoryImpl implements ConciertoRepository {
    private static final String FILE_NAME = "conciertos.dat";

    @Override
    public List<Concierto> findAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Concierto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Concierto findById(int id) {
        return findAll().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Concierto concierto) {
        List<Concierto> conciertos = findAll();
        conciertos.add(concierto);
        saveAll(conciertos);
    }

    @Override
    public void update(Concierto concierto) {
        List<Concierto> conciertos = findAll();
        conciertos = conciertos.stream()
                .map(p -> p.getId() == concierto.getId() ? concierto : p)
                .collect(Collectors.toList());
        saveAll(conciertos);
    }

    @Override
    public void delete(int id) {
        List<Concierto> conciertos = findAll();
        conciertos = conciertos.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        saveAll(conciertos);
    }

    private void saveAll(List<Concierto> conciertos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(conciertos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
