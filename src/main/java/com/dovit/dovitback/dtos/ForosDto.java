package com.dovit.dovitback.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//-> YAN SOTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForosDto {
    private Long id;
    private String titulo;
    private Long ProyectoId;
}
