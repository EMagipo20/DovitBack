package com.dovit.dovitback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Donaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDonaciones")
    private Long id;

    @Column(name = "cantidadMonetaria")
    private Integer cantidadMonetaria;

    @Column(name = "fechaDonacion")
    @Temporal(TemporalType.DATE)
    private Date fechaDonacion;

    @Column(name = "metodoDePago")
    private String metodoDePago;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "idDonante", nullable = false)
    private Donante donante;

    @ManyToOne
    @JoinColumn(name = "idProyecto", nullable = false)
    private Proyectos proyectos;
}