package com.dovit.dovitback.model;

import jakarta.persistence.*;
import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyectos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProyectos")
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @NotNull(message = "El monto objetivo no puede ser nulo")
    @Min(value = 1, message = "El monto objetivo debe ser mayor que cero")
    @Column(name = "monto_objetivo")
    private Double montoObjetivo;

    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Column(name = "categoria")
    private String categoria;

    //Relaciones
    @ManyToOne
    @JoinColumn(name = "idOrganizacion", nullable = false)
    private Organizacion organizacion;
}
