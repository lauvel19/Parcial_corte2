package org.arle.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "conciertos")
@EntityListeners(ConciertoListener.class)
public class Concierto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String fecha;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Concierto" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha;
    }
}
