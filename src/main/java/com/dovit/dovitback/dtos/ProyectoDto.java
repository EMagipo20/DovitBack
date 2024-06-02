package com.dovit.dovitback.dtos;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

//-> KIARA
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoDto {
    private Long id;
    private String titulo;
    private String descripcion;
    @NotNull(message = "El monto objetivo no puede ser nulo")
    @Min(value = 1, message = "El monto objetivo debe ser mayor que cero")
    private Double monto_Objetivo;
    private Date fechaInicio;
    private Date fechaFin;
    private String categoria;
    private Long OrganizacionBeneficaId;
}
