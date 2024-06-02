package com.dovit.dovitback.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDonante")
    private Long id;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    @Column(name = "contactoTelefonico")
    private String contactoTelefonico;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "direccion")
    private String direccion;

    //Relaciones
    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
}