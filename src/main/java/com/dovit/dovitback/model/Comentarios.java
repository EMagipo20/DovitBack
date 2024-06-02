package com.dovit.dovitback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComentario")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "idProyecto", nullable = false)
    private Proyectos proyecto;

    @ManyToOne
    @JoinColumn(name = "idDonante", nullable = false)
    private Donante donante;
}