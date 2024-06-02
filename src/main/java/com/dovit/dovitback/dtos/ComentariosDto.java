package com.dovit.dovitback.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//-> DIEGO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentariosDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private Long ProyectoId;
    private Long DonanteId;
}
