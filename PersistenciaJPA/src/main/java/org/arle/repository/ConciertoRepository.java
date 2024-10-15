package org.arle.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.arle.entity.Concierto;

import java.util.List;

public class ConciertoRepository {

    private final EntityManagerFactory emf;

    public ConciertoRepository() {

        emf = Persistence.createEntityManagerFactory("conciertoPU");
    }
    public void crear(Concierto concierto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(concierto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Concierto leer(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Concierto.class, id);
        } finally {
            em.close();
        }
    }

    public List<Concierto> leerTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("SELECT p FROM Concierto p", Concierto.class)
                    .getResultList();
        }
    }

    public void actualizar(Concierto concierto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(concierto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Concierto concierto = em.find(Concierto.class, id);
            if (concierto != null) {
                em.remove(concierto);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void cerrar() {
        emf.close();
    }
}