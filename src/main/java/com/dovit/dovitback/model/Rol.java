package com.dovit.dovitback.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRol")
    private int idRol;

    @Column(name = "rol")
    private String rol;

    public Rol() {
    }
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public int getId() {
        return idRol;
    }

    public void setId(int id) {
        this.idRol = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    // Relaciones
    @ManyToOne(optional = false)  // Asegúrate de que esta relación no sea opcional
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario user;
}
