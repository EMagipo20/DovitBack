package com.dovit.dovitback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Foros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idForo")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "idProyecto", nullable = false)
    private Proyectos proyecto;
}
