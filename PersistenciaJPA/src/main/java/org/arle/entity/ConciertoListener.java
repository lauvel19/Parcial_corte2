package org.arle.entity;

import jakarta.persistence.*;

public class ConciertoListener {

    @PrePersist
    public void prePersist(Concierto concierto) {
        System.out.println("Concierto a ser persistido: " + concierto);
    }

    @PostPersist
    public void postPersist(Concierto concierto) {
        System.out.println("Concierto persistido: " + concierto);
    }

    @PreUpdate
    public void preUpdate(Concierto concierto) {
        System.out.println("Concierto a ser actualizado: " + concierto);
    }

    @PostUpdate
    public void postUpdate(Concierto concierto) {
        System.out.println("Concierto actualizado: " + concierto);
    }

    @PreRemove
    public void preRemove(Concierto concierto) {
        System.out.println("Concierto a ser eliminado: " + concierto);
    }

    @PostRemove
    public void postRemove(Concierto concierto) {
        System.out.println("Concierto eliminado: " + concierto);
    }
}
