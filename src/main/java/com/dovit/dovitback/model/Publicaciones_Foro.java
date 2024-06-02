package com.dovit.dovitback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicaciones_Foro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPublicaciones")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "contenido")
    private String contenido;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "idOrganizacion", nullable = false)
    private Organizacion organizacion;

    @ManyToOne
    @JoinColumn(name = "idForo", nullable = false)
    private Foros foros;
}