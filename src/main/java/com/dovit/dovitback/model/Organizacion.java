package com.dovit.dovitback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrganizacion")
    private Long id;

    @Column(name = "nombreOrganizacion")
    private String nombreOrganizacion;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "cantidadProyectos")
    private int cantidadProyectos;

    @Column(name = "nombreRepresentante")
    private String nombreRepresentante;

    @Column(name = "numeroTelefonicoRepresentante")
    private String numeroTelefonicoRepresentante;

    @Column(name = "correoElectronicoRepresentante")
    private String correoElectronicoRepresentante;

    //Relaciones
    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Proyectos> proyectos;
}
